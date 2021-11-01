package com.stevedevblog.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
@Controller
@RequestMapping(AboutController.PATH)
public class AboutController {
    public static final String PATH = "/about";
    private static final String PAGE_TITLE = "About";

    @GetMapping
    public String getAboutPage(Model model) {
        model.addAttribute("pageTitle", PAGE_TITLE);
        return "about";
    }
}
