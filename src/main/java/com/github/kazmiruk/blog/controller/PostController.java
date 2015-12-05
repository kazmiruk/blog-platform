package com.github.kazmiruk.blog.controller;

import com.github.kazmiruk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post-index";
    }

    @RequestMapping("/{id}/{translated_title}")
    public String view(@PathVariable int id, @PathVariable String translated_title) {
        return "post-view";
    }
}
