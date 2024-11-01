package com.feedback.response;

import com.feedback.model.User;

public class LoginResponse {
    private String message;
    private String token;
    private User user;

    // Constructor for success
    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
        this.message = "Login successful";
    }

    // Constructor for errors
    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
