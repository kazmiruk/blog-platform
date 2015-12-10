package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.service.CommentaryService;
import com.github.kazmiruk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentaryService commentaryService;

    @RequestMapping("/{id}")
    public String detail(Model model, @PathVariable int id,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("commentaryPage", commentaryService.getUserCommentaries(user, page <= 1? 0: page - 1));
        return "user-detail";
    }

    @RequestMapping
    public String account(Model model, Principal principal,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        User user = userService.findOne(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("commentaryPage", commentaryService.getUserCommentaries(user, page <= 1? 0: page - 1));
        return "user-account";
    }
}
