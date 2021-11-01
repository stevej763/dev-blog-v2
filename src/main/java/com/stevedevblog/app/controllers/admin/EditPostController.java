package com.stevedevblog.app.controllers.admin;

import com.stevedevblog.app.domain.EditPostRequest;
import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(EditPostController.PATH)
public class EditPostController {

    public static final String PATH = "/edit-post";
    private final BlogPostService blogPostService;

    public EditPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("{id}")
    public String getEditPostPage(@PathVariable String id, Model model) {
        ExistingBlogPostResponse post = blogPostService.getPostById(id);
        if (post != null) {
            model.addAttribute("pageTitle", "Edit Post");
            model.addAttribute("postId", post.getId());
            model.addAttribute("postTitle", post.getTitle());
            model.addAttribute("postDescription", post.getDescription());
            model.addAttribute("postHeaderImage", post.getHeaderImageUrl());
            model.addAttribute("postContent", post.getPostContent());
            model.addAttribute("publishDate", post.getPrettyDate());
            model.addAttribute("postCategory", post.getCategory());
            return "admin/edit-post";
        } else {
            return "redirect:/error";
        }

    }

    @PostMapping
    public String postEditBlogPostForm(EditPostRequest editPostRequest) {
        PersistedBlogPost result = blogPostService.updatePost(editPostRequest);
        if (result == null) {
            System.out.println("TODO handle this better.");
            System.out.println("There was an error updating the post.");
        }
        return "redirect:/";
    }

}
