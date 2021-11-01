package com.stevedevblog.app.domain.builders;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    private static String convertToPrettyDate(LocalDateTime publishDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
        return formatter.format(publishDate);
    }

}
