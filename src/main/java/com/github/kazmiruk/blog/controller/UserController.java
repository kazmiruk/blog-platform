package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public String detail(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.findOneWithCommentaries(id));
        return "user-detail";
    }

    @RequestMapping
    public String account(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("user", userService.findOneWithCommentaries(email));
        return "user-account";
    }
}
