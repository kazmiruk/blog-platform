package com.github.kazmiruk.blog.controller;

import com.github.kazmiruk.blog.entity.Commentary;
import com.github.kazmiruk.blog.service.CommentaryService;
import com.github.kazmiruk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class CommentaryController {
    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    private PostService postService;

    @ModelAttribute("commentary")
    public Commentary construct() {
        return new Commentary();
    }

    @RequestMapping(value = "/post/{id}/commentary", method = RequestMethod.POST)
    public String doCreate(Model model, @PathVariable int id,
                           @Valid @ModelAttribute("commentary") Commentary commentary,
                           BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("post", postService.findOneWithCommentaries(id));
            return "post-view";
        }

        commentary.setPost(postService.findOne(id));
        commentaryService.save(principal, commentary);
        return "redirect:/post/" + String.valueOf(id);
    }
}
