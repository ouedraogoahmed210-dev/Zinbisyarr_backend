package com.example.demo.security;

public class AuthResponse {

    private String token;
    private String role;
    private Long userId;
    private String nom;

    public AuthResponse(String token, String role, Long userId, String nom) {
        this.token = token;
        this.role = role;
        this.userId = userId;
        this.nom = nom;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}