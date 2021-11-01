package com.stevedevblog.app.domain.builders;

import com.stevedevblog.app.domain.PersistedBlogPost;
import com.stevedevblog.app.domain.PostCategory;

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
        LocalDateTime publishDate = LocalDateTime.now();
        return new PersistedBlogPost(id, title, description, headerImageUrl, postContent, publishDate, category);
    }

}
