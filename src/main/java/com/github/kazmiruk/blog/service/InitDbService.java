package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.*;
import com.github.kazmiruk.blog.repository.*;
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

    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);

            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);

            User userAdmin = new User();
            userAdmin.setEmail("admin@localhost");
            userAdmin.setEnabled(true);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userAdmin.setPassword(encoder.encode("admin"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            userAdmin.setRoles(roles);
            userRepository.save(userAdmin);

            Tag tag1 = new Tag();
            tag1.setName("test1");
            tag1.setClassName("label-primary");
            tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setName("test2");
            tag2.setClassName("label-info");
            tagRepository.save(tag2);

            Post testPost = new Post();
            testPost.setTitle("Test post");
            testPost.setContent("Content of test post");
            testPost.setPublishedDate(new Date());
            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);
            testPost.setTags(tags);
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
}
