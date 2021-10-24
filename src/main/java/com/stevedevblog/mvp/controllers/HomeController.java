package com.stevedevblog.mvp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String getHomepage(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

}
