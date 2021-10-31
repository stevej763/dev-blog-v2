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
//        List<PersistedBlogPost> posts = List.of(
//                blogPostService.getPostById("946a73ef-a24d-479c-bda7-0f62289adb87").get(),
//                blogPostService.getPostById("3e1ec8a6-325e-4bd8-9d12-f36ceab76b55").get(),
//                blogPostService.getPostById("911df52d-cb04-4dca-add6-c963b7ec09d4").get()
//        );
        List<PersistedBlogPost> posts = Collections.emptyList();

        return posts;
    }

}
