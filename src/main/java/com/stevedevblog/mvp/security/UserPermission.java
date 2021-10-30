package com.stevedevblog.mvp.security;

public enum UserPermission {
    POST_READ("read"),
    POST_WRITE("write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
