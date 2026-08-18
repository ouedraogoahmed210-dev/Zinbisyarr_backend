package com.example.demo.security;

public class AuthenticatedUser {

    private Long userId;
    private String telephone;
    private String role;

    public AuthenticatedUser(Long userId, String telephone, String role) {
        this.userId = userId;
        this.telephone = telephone;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getRole() {
        return role;
    }

    public boolean estAdmin() {
        return "ADMIN".equals(role);
    }
}