package com.stevedevblog.mvp.service;

import com.stevedevblog.mvp.domain.NewPostResponse;
import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.domain.PersistedBlogPostBuilder;
import com.stevedevblog.mvp.repository.BlogPostRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public PersistedBlogPost addPost(NewPostResponse newPostResponse) {
        PersistedBlogPost persistedBlogPost = convertToPersistedObject(newPostResponse);
        try {
            return blogPostRepository.insert(persistedBlogPost);
        } catch (Exception e) {
            return null;
        }
    }

    private PersistedBlogPost convertToPersistedObject(NewPostResponse newPostResponse) {
        return new PersistedBlogPostBuilder().build(
            newPostResponse.getTitle(),
            newPostResponse.getDescription(),
            newPostResponse.getHeaderImageUrl(),
            newPostResponse.getPostContent(),
            newPostResponse.getCategory());
    }

    public List<PersistedBlogPost> getPosts() {
        List<PersistedBlogPost> posts = blogPostRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }
}
