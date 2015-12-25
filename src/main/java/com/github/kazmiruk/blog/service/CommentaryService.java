package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;


@Service
@Transactional
public class CommentaryService {
    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${pageSize}")
    private int pageSize;

    @Transactional
    public void save(Principal principal, Commentary commentary) {
        User user = null;

        if (principal != null) {
            user = userRepository.findByEmail(principal.getName());
        }

        commentary.setUser(user);
        commentary.setPublishedDate(new Date());
        commentaryRepository.save(commentary);
    }

    @Transactional
    public Page<Commentary> getUserCommentaries(User user, int page) {
        return commentaryRepository.findByUser(
                user, new PageRequest(page, pageSize, Sort.Direction.DESC, "publishedDate"));
    }
}
