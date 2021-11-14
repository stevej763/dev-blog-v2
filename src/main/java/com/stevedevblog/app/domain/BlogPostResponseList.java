package com.stevedevblog.app.domain;

import java.util.List;

public class BlogPostResponseList {

    private List<ExistingBlogPostResponse> posts;

    public BlogPostResponseList(List<ExistingBlogPostResponse> posts) {
        this.posts = posts;
    }

    public List<ExistingBlogPostResponse> getPosts() {
        return posts;
    }
}
