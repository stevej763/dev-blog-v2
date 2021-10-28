package com.stevedevblog.mvp.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class PersistedBlogPostBuilder {

    public PersistedBlogPost build(
            String title,
            String description,
            String headerImageUrl,
            String postContent,
            PostCategory category) {
        String id = UUID.randomUUID().toString();
        Date publishDate = new Date();
        return new PersistedBlogPost(id, title, description, headerImageUrl, postContent, publishDate, category);
    }

}
