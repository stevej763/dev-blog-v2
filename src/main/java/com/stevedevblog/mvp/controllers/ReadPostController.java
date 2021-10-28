package com.stevedevblog.mvp.controllers;

import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(ReadPostController.PATH)
public class ReadPostController {

    public static final String PATH = "/blog-post";
    private final BlogPostService blogPostService;

    public ReadPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("{id}")
    public String getReadPostPage(@PathVariable String id, Model model) {
        Optional<PersistedBlogPost> post = blogPostService.getPostById(id);
        if (post.isPresent()) {

            return returnPage(id, model, post);
        } else {
            return "redirect:/";
        }
    }

    private String returnPage(String id, Model model, Optional<PersistedBlogPost> post) {
        model.addAttribute("pageTitle", id);
        model.addAttribute("post", post.get());
        return "read-post";
    }

}