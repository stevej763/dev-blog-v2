package com.stevedevblog.app.controllers.admin;

import com.stevedevblog.app.domain.NewPostRequest;
import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "admin/new-post";
    }

    @PostMapping
    public String postNewPost(NewPostRequest newPostRequest) {
        PersistedBlogPost result = blogPostService.addPost(newPostRequest);
        if (result == null) {
            System.out.println("TODO handle this better.");
            System.out.println("There was an error adding new post.");
        }
        return "redirect:/";
    }

}
