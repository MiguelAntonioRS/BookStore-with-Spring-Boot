package com.bookStore.bookStore.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "BookStoreSecretKey2024VeryLongAndSecureKeyForJWTTokenGeneration123456789");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 86400000L);
        
        userDetails = User.builder()
                .username("testuser")
                .password("password")
                .roles("USER")
                .build();
    }

    @Test
    @DisplayName("Should generate valid JWT token")
    void generateToken_Success() {
        String token = jwtService.generateToken(userDetails);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    @DisplayName("Should extract username from token")
    void extractUsername_Success() {
        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUsername(token);
        
        assertEquals("testuser", username);
    }

    @Test
    @DisplayName("Should validate token successfully")
    void isTokenValid_Success() {
        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        
        assertTrue(isValid);
    }

    @Test
    @DisplayName("Should return false for invalid token")
    void isTokenValid_InvalidToken() {
        boolean isValid = jwtService.isTokenValid("invalid.token.here", userDetails);
        
        assertFalse(isValid);
    }

    @Test
    @DisplayName("Should return expiration time")
    void getExpirationTime_Success() {
        long expiration = jwtService.getExpirationTime();
        
        assertEquals(86400000L, expiration);
    }
}