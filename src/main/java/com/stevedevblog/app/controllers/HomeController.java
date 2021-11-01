package com.stevedevblog.app.controllers;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

import static com.stevedevblog.app.controllers.HomeController.PATH;

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
        List<ExistingBlogPostResponse> latestPosts = blogPostService.getLatestPosts();
        List<PersistedBlogPost> featuredPosts = getFeaturedPosts();
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("latestPosts", latestPosts);
        model.addAttribute("featuredPosts", featuredPosts);
        return "index";
    }

    private List<PersistedBlogPost> getFeaturedPosts() {
        return Collections.emptyList();
    }

}
