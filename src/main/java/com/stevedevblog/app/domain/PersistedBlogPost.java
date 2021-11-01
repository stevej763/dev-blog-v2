package com.stevedevblog.app.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PersistedBlogPost {

    @Id
    private final String id;

    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;
    private final LocalDateTime publishDate;
    private final PostCategory category;

    public PersistedBlogPost(String id,
                             String title,
                             String description,
                             String headerImageUrl,
                             String postContent,
                             LocalDateTime publishDate,
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

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistedBlogPost)) return false;
        PersistedBlogPost that = (PersistedBlogPost) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
