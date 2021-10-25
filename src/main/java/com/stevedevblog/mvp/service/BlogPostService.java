package com.stevedevblog.mvp.service;

import com.stevedevblog.mvp.domain.NewPost;
import com.stevedevblog.mvp.repository.BlogPostRepository;
import org.springframework.stereotype.Component;

@Component
public class BlogPostService {

    private BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public void addPost(NewPost newPost) {

    }
}
