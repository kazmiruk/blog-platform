package com.github.kazmiruk.blog.repository;

import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findByPosts(List<Post> posts);
}
