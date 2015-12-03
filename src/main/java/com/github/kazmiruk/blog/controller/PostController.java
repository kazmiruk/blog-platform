package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.service.PostService;
import com.github.kazmiruk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @ModelAttribute("post")
    public Post construct() {
        return new Post();
    }

    @RequestMapping("/post")
    public String createPost() {
        return "create-post";
    }

    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String doCreatePost(@ModelAttribute("post") Post post, Principal principal) {
        String userName = principal.getName();
        postService.save(userName, post);
        return "redirect:/";
    }

    @RequestMapping("/post/remove/{id}")
    public String removePost(@PathVariable int id) {
        Post post = postService.findOne(id);
        postService.delete(post);
        return "redirect:/";
    }
}
