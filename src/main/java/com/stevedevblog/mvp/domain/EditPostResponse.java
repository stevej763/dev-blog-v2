package com.stevedevblog.mvp.domain;

import java.util.Date;

public class EditPostResponse {


    private final String id;
    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;
    private final Date publishDate;
    private final PostCategory category;

    public EditPostResponse(String id,
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

    public PostCategory getCategory() {
        return category;
    }

    public String getId() {
        return id;
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

    public Date getPublishDate() {
        return publishDate;
    }
}
