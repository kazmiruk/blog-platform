package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.PostRepository;
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

    @Autowired
    private CommentaryRepository commentaryRepository;

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

    public Post findOneWithCommentaries(int id) {
        Post post = postRepository.findOne(id);
        List<Commentary> commentaries = commentaryRepository.findByPost(post);
        post.setCommentaries(commentaries);

        return post;
    }

    public List<Post> findAllWithCommentaries() {
        List<Post> posts = postRepository.findAll(
                new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate")).getContent();

        for(Post post: posts) {
            post.setCommentaries(commentaryRepository.findByPost(post));
        }

        return posts;
    }
}
