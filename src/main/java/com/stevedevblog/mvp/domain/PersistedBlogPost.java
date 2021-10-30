package com.stevedevblog.mvp.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class PersistedBlogPost {

    @Id
    private final String id;

    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;
    private final Date publishDate;
    private final PostCategory category;

    public PersistedBlogPost(String id,
                             String title,
                             String description,
                             String headerImageUrl,
                             String postContent,
                             Date publishDate,
                             PostCategory category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.headerImageUrl = headerImageUrl;
        this.postContent = postContent;
        this.publishDate = publishDate;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public String getPostContent() {
        return postContent;
    }

    public PostCategory getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
