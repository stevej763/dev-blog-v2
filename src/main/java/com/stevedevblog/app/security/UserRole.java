package com.stevedevblog.app.security;

import java.util.Set;

import static com.stevedevblog.app.security.UserPermission.*;

public enum UserRole {
    ADMIN(Set.of(POST_READ, POST_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
