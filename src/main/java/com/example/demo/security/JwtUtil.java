package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String genererToken(String telephone, String role, Long userId) {
        return Jwts.builder()
                .subject(telephone)
                .claim("role", role)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extraireTelephone(String token) {
        return extraireClaim(token, Claims::getSubject);
    }

    public String extraireRole(String token) {
        return extraireClaim(token, claims -> claims.get("role", String.class));
    }

    public Long extraireUserId(String token) {
        return extraireClaim(token, claims -> claims.get("userId", Long.class));
    }

    private <T> T extraireClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extraireToutesLesClaims(token);
        return resolver.apply(claims);
    }

    private Claims extraireToutesLesClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean estValide(String token) {
        try {
            extraireToutesLesClaims(token);
            return !estExpire(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean estExpire(String token) {
        Date expirationDate = extraireClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
}