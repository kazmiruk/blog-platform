package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.repository.PostRepository;
import com.github.kazmiruk.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void save(Post post) {
        post.setPublishedDate(new Date());
        postRepository.save(post);
    }

    public void delete(@P("post") Post post) {
        postRepository.delete(post);
    }

    public Post findOne(int id) {
        return postRepository.findOne(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll(new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate")).getContent();
    }
}
