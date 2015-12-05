package com.github.kazmiruk.blog.validator;


import com.github.kazmiruk.blog.annotation.UniqueUserEmail;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userRepository == null) {
            // only during InitDbService working
            return true;
        }

        return userRepository.findByEmail(value) == null;
    }
}
