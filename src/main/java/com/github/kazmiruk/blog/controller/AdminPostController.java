package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/post")
public class AdminPostController {
    @Autowired
    private PostService postService;

    @ModelAttribute("post")
    public Post construct() {
        return new Post();
    }

    @RequestMapping("/create")
    public String create() {
        return "admin-post-create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-post-create";
        }

        postService.save(post);
        return "redirect:/";
    }
}
