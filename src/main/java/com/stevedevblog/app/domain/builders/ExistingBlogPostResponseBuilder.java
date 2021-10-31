package com.stevedevblog.app.domain.builders;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;

import java.util.Date;
import java.util.Optional;

public class ExistingBlogPostResponseBuilder {
    public static ExistingBlogPostResponse build(PersistedBlogPost persistedBlogPost) {
        return new ExistingBlogPostResponse(
                persistedBlogPost.getId(),
                persistedBlogPost.getTitle(),
                persistedBlogPost.getDescription(),
                persistedBlogPost.getHeaderImageUrl(),
                persistedBlogPost.getPostContent(),
                convertToPrettyDate(persistedBlogPost.getPublishDate()),
                persistedBlogPost.getCategory());
    }

    private static String convertToPrettyDate(Date publishDate) {
        return null;
    }

}
