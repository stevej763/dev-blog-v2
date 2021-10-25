package com.stevedevblog.mvp.controllers;

import com.stevedevblog.mvp.domain.NewPost;
import com.stevedevblog.mvp.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping(NewPostController.PATH)
public class NewPostController {

    public static final String PATH = "/new-post";
    private final BlogPostService blogPostService;

    public NewPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String getNewPostPage(Model model) {
        model.addAttribute("pageTitle", "New Post");
        return "new-post";
    }

    @PostMapping("/new-post")
    public String postNewPost(NewPost newPost) {
        System.out.println(newPost);
        System.out.println(newPost.getPostContent());
//        blogPostService.addPost(blogPost);
        return "redirect:/new-post";
    }

}
