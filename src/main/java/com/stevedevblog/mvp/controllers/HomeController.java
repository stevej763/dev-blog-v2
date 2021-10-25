package com.stevedevblog.mvp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.stevedevblog.mvp.controllers.HomeController.*;

@Controller
@RequestMapping(PATH)
public class HomeController {

    public static final String PATH = "/";

    @GetMapping
    public String getHomepage(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

}
