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
    private PostRepository postRepository;

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

    protected User populateUserWithPosts(User user) {
        List<Post> posts = postRepository.findByUser(user);

        for(Post post: posts) {
            List<Commentary> commentaries = commentaryRepository.findByPost(
                    post, new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate"));
            post.setCommentaries(commentaries);
        }
        user.setPosts(posts);

        return user;
    }

    @Transactional
    public User findOneWithPosts(int id) {
        User user = findOne(id);
        return populateUserWithPosts(user);
    }

    @Transactional
    public User findOneWithPosts(String name) {
        User user = userRepository.findByName(name);
        return populateUserWithPosts(user);
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
