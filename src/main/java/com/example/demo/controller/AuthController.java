package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.AuthResponse;
import com.example.demo.security.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/connexion")
    public AuthResponse seConnecter(@RequestParam String telephone, @RequestParam String motDePasse) {
        return authService.seConnecter(telephone, motDePasse);
    }
}