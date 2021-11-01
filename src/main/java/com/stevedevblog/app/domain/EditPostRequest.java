package com.stevedevblog.app.domain;

import java.util.Date;

public class EditPostRequest {


    private final String id;
    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;

    public EditPostRequest(String id,
                           String title,
                           String description,
                           String headerImageUrl,
                           String postContent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.headerImageUrl = headerImageUrl;
        this.postContent = postContent;
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

}
