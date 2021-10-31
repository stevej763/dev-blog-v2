package com.stevedevblog.app.controllers.admin;

import com.stevedevblog.app.domain.EditPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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
        Optional<PersistedBlogPost> persistedPost = blogPostService.getPostById(id);
        if (persistedPost.isPresent()) {
            PersistedBlogPost post = persistedPost.get();
            model.addAttribute("pageTitle", "Edit Post");
            model.addAttribute("postId", post.getId());
            model.addAttribute("postTitle", post.getTitle());
            model.addAttribute("postDescription", post.getDescription());
            model.addAttribute("postHeaderImage", post.getHeaderImageUrl());
            model.addAttribute("postContent", post.getPostContent());
            model.addAttribute("publishDate", post.getPublishDate());
            model.addAttribute("postCategory", post.getCategory());
            return "admin/edit-post";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping
    public String postEditBlogPostForm(EditPostResponse editPostResponse) {
        PersistedBlogPost result = blogPostService.updatePost(editPostResponse);
        if (result == null) {
            System.out.println("TODO handle this better.");
            System.out.println("There was an error updating the post.");
        }
        return "redirect:/";
    }

}
