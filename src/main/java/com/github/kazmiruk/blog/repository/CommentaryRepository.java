package com.github.kazmiruk.blog.repository;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
    Page<Commentary> findByUser(User user, Pageable publishedDate);

    List<Commentary> findByPost(Post post);
}
