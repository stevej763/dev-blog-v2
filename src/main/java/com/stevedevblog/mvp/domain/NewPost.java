package com.stevedevblog.mvp.domain;

public class NewPost {

    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;
    private final PostCategory category;

    public NewPost(String title,
                   String description,
                   String headerImageUrl,
                   String postContent,
                   PostCategory category) {
        this.title = title;
        this.description = description;
        this.headerImageUrl = headerImageUrl;
        this.postContent = postContent;
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
}
