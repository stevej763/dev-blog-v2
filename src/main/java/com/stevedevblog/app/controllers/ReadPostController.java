package com.stevedevblog.app.controllers;

import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import com.stevedevblog.app.service.MarkdownRenderer;
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
    private final MarkdownRenderer markdownRenderer;

    public ReadPostController(BlogPostService blogPostService, MarkdownRenderer markdownRenderer) {
        this.blogPostService = blogPostService;
        this.markdownRenderer = markdownRenderer;
    }

    @GetMapping("{id}")
    public String getReadPostPage(@PathVariable String id, Model model) {
        Optional<PersistedBlogPost> post = blogPostService.getPostById(id);
        return post.map(persistedBlogPost -> returnPage(id, model, persistedBlogPost)).orElse("redirect:/");
    }

    private String returnPage(String id, Model model, PersistedBlogPost post) {
        String content = post.getPostContent();
        String markdown = markdownRenderer.markdownToHTML(content);
        model.addAttribute("pageTitle", post.getTitle());
        model.addAttribute("headerImageUrl", post.getHeaderImageUrl());
        model.addAttribute("post", post);
        model.addAttribute("markdown-content", markdown);
        return "read-post";
    }

}