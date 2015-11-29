package com.github.kazmiruk.blog.repository;


import com.github.kazmiruk.blog.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
}
