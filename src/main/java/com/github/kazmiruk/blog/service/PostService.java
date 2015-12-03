package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.repository.PostRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(String userName, Post post) {
        User user = userRepository.findByName(userName);
        post.setUser(user);
        post.setPublishedDate(new Date());
        postRepository.save(post);
    }

    @PreAuthorize("#post.user.name == authentication.name or hasRole('ROLE_ADMIN')")
    public void delete(@P("post") Post post) {
        postRepository.delete(post);
    }

    public Post findOne(int id) {
        return postRepository.findOne(id);
    }
}
