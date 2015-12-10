package com.github.kazmiruk.blog.controller;

import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-user-list";
    }

    // TODO: method DELETE and js for link to call it
    @RequestMapping(value = "/remove/{id}")
    public String remove(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin/user";
    }
}
