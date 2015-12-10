package com.github.kazmiruk.blog.controller;

import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @ModelAttribute("commentary")
    public Commentary construct() {
        return new Commentary();
    }


    @RequestMapping("/")
    public String list(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Page<Post> postPage = postService.findAllWithCommentaries(page <= 1? 0: page - 1);
        model.addAttribute("postPage", postPage);
        return "post-index";
    }

    @RequestMapping("/post/{id}")
    public String view(Model model, @PathVariable int id) {
        model.addAttribute("post", postService.findOneWithCommentaries(id));
        return "post-view";
    }
}
