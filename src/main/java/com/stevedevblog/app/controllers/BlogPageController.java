package com.stevedevblog.app.controllers;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.stevedevblog.app.controllers.BlogPageController.PATH;

@Controller
@RequestMapping(PATH)
public class BlogPageController {

    public static final String PATH = "/blog";
    public static final String PAGE_TITLE = "Blog";
    private final BlogPostService blogPostService;

    public BlogPageController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String getBlogPage(Model model) {
        List<ExistingBlogPostResponse> allPosts = blogPostService.getPosts();
        model.addAttribute("pageTitle", PAGE_TITLE);
        model.addAttribute("posts", allPosts);
        return "blog";
    }

}
