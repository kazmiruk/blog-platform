package com.github.kazmiruk.blog.repository;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
    List<Commentary> findByUser(User user, PageRequest publishedDate);

    List<Commentary> findByPost(Post post);
}
