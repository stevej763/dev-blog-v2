package com.stevedevblog.mvp.controllers;

import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.stevedevblog.mvp.controllers.HomeController.*;

@Controller
@RequestMapping(PATH)
public class HomeController {

    public static final String PATH = "/";
    private final BlogPostService blogPostService;

    public HomeController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String getHomepage(Model model) {
        List<PersistedBlogPost> posts = blogPostService.getPosts();
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("posts", posts);
        return "index";
    }

}
