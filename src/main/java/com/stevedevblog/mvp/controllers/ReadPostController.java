package com.stevedevblog.mvp.controllers;

import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

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
        return post.map(persistedBlogPost -> returnPage(id, model, persistedBlogPost)).orElse("redirect:/");
    }

    private String returnPage(String id, Model model, PersistedBlogPost post) {
        String content = post.getPostContent();
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String markup = renderer.render(document);
        model.addAttribute("pageTitle", post.getTitle());
        model.addAttribute("post", post);
        model.addAttribute("markup-content", markup);
        return "read-post";
    }

}