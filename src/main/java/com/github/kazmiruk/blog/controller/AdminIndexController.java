package com.github.kazmiruk.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
    @RequestMapping
    public String index() {
        return "admin-index";
    }
}