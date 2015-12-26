package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.Tag;
import com.github.kazmiruk.blog.repository.CommentaryRepository;
import com.github.kazmiruk.blog.repository.PostRepository;
import com.github.kazmiruk.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Value("${pageSize}")
    private int pageSize;

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
        List<Post> postSelector = new ArrayList<>();
        postSelector.add(post);
        post.setTags(tagRepository.findByPosts(postSelector));

        return post;
    }

    public Post findOneWithCommentariesAndTags(int id) {
        Post post = findOneWithCommentaries(id);
        List<Post> postSelector = new ArrayList<>();
        postSelector.add(post);
        post.setTags(tagRepository.findByPosts(postSelector));

        return post;
    }

    public Page<Post> findAllWithCommentaries(int page) {
        Page<Post> postPage = postRepository.findAll(
                new PageRequest(page, pageSize, Sort.Direction.DESC, "publishedDate"));
        List<Post> posts = postPage.getContent();

        for(Post post: posts) {
            post.setCommentaries(commentaryRepository.findByPost(post));
            List<Post> postSelector = new ArrayList<>();
            postSelector.add(post);
            post.setTags(tagRepository.findByPosts(postSelector));
        }

        return postPage;
    }
}
