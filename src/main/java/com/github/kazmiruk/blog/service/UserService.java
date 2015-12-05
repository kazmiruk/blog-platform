package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Role;
import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.RoleRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    protected User populateUserWithCommentaries(User user) {
        List<Commentary> commentaries = commentaryRepository.findByUser(
            user, new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate"));
        user.setCommentaries(commentaries);

        return user;
    }

    @Transactional
    public User findOneWithCommentaries(int id) {
        User user = findOne(id);
        return populateUserWithCommentaries(user);
    }

    @Transactional
    public User findOneWithCommentaries(String email) {
        User user = findOne(email);
        return populateUserWithCommentaries(user);
    }

    public void save(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }
}
