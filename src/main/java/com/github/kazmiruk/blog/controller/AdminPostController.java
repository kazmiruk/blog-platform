package com.github.kazmiruk.blog.controller;


import com.github.kazmiruk.blog.entity.Post;
import com.github.kazmiruk.blog.entity.Tag;
import com.github.kazmiruk.blog.service.PostService;
import com.github.kazmiruk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin/post")
public class AdminPostController {
    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @ModelAttribute("post")
    public Post construct() {
        return new Post();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception{
        binder.registerCustomEditor(List.class, "tags", new CustomCollectionEditor(List.class){
            @Override
            protected boolean alwaysCreateNewCollection() {
                return true;
            }

            protected Object convertElement(Object element){
                if (element instanceof String) {
                    return tagService.findOne(Integer.parseInt(element.toString()));
                } else if (element instanceof Tag) {
                    return ((Tag) element).getId().toString();
                }
                return null;
            }
        });
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "admin-post-create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("tags", tagService.findAll());
            return "admin-post-create";
        }

        postService.save(post);
        return "redirect:/";
    }
}
