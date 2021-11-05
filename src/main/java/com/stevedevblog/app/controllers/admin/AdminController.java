package com.stevedevblog.app.controllers.admin;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(AdminController.PATH)
public class AdminController {

    public static final String PATH = "/admin";
    public static final String PAGE_TITLE = "Admin";
    private BlogPostService blogPostService;

    @Autowired
    public AdminController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        List<ExistingBlogPostResponse> posts = blogPostService.getPosts();
        model.addAttribute("pageTitle", PAGE_TITLE);
        model.addAttribute("posts", posts);
        return "admin/admin";
    }

    @GetMapping("edit-post")
    public String getEditPostPage(@RequestParam String postToEdit, Model model) {
        System.out.println(postToEdit);
        return "redirect:/edit-post/" + postToEdit;
    }

}
