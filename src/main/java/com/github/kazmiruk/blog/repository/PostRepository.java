package com.github.kazmiruk.blog.repository;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
}
