package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.Role;
import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.PostRepository;
import com.github.kazmiruk.blog.repository.RoleRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class InitDbService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @PostConstruct
    public void init() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setName("admin");
        userAdmin.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userAdmin.setPassword(encoder.encode("admin"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        userAdmin.setRoles(roles);
        userRepository.save(userAdmin);

        Post testPost = new Post();
        testPost.setTitle("Test post");
        testPost.setContent("Content of test post");
        testPost.setPublishedDate(new Date());
        testPost.setUser(userAdmin);
        postRepository.save(testPost);

        Commentary test_commentary1 = new Commentary();
        test_commentary1.setContent("Test commentary");
        test_commentary1.setPublishedDate(new Date());
        test_commentary1.setPost(testPost);
        test_commentary1.setUser(userAdmin);
        commentaryRepository.save(test_commentary1);

        Commentary test_commentary2 = new Commentary();
        test_commentary2.setContent("Test commentary");
        test_commentary2.setPublishedDate(new Date());
        test_commentary2.setPost(testPost);
        test_commentary2.setUser(userAdmin);
        commentaryRepository.save(test_commentary2);
    }
}
