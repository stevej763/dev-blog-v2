package com.stevedevblog.mvp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewPostController {

    @GetMapping("/new-post")
    public String getNewPostPage(Model model) {
        model.addAttribute("pageTitle", "New Post");
        return "new-post";
    }

}
