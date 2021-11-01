package com.stevedevblog.app.domain;

import java.util.Objects;

public class ExistingBlogPostResponse {

    private final String id;
    private final String title;
    private final String description;
    private final String headerImageUrl;
    private final String postContent;
    private final String prettyDate;
    private final PostCategory category;

    public ExistingBlogPostResponse(String id,
                                    String title,
                                    String description,
                                    String headerImageUrl,
                                    String postContent,
                                    String prettyDate,
                                    PostCategory category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.headerImageUrl = headerImageUrl;
        this.postContent = postContent;
        this.prettyDate = prettyDate;
        this.category = category;
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

    public String getPrettyDate() {
        return prettyDate;
    }

    public PostCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExistingBlogPostResponse)) return false;
        ExistingBlogPostResponse that = (ExistingBlogPostResponse) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
