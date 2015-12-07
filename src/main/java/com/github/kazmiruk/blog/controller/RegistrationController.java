package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.entity.User;
import com.github.kazmiruk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @RequestMapping
    public String register() {
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return register();
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/registration";
    }

    @RequestMapping("/available")
    @ResponseBody
    public String availableEmail(@RequestParam String email) {
        Boolean available = userService.findOne(email) == null;
        return available.toString();
    }
}
