package com.stevedevblog.mvp.domain;

public enum PostCategory {
    BUILD_LOG("Build Log"),
    TESTING("Testing"),
    DESIGN_PATTERNS("Design Patterns");

    private final String value;

    PostCategory(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
