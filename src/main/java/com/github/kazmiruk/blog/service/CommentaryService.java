package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class CommentaryService {
    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(String email, Commentary commentary) {
        commentary.setUser(userRepository.findByEmail(email));
        commentary.setPublishedDate(new Date());
        commentaryRepository.save(commentary);
    }
}
