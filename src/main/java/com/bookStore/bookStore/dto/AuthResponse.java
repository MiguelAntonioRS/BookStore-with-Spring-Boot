package com.bookStore.bookStore.dto;

import com.bookStore.bookStore.entity.User;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private String role;

    public AuthResponse() {}

    public AuthResponse(String token, User user) {
        this.token = token;
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole() != null ? user.getRole().getName() : "USER";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}