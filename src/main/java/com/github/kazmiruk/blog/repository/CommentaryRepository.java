package com.github.kazmiruk.blog.repository;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
    List<Commentary> findByPost(Post post, Pageable pageable);
}
