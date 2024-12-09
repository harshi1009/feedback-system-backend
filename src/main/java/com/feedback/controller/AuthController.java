package com.feedback.controller;

import com.feedback.model.User;
import com.feedback.request.ForgotPasswordRequest;
import com.feedback.request.ResetPasswordRequest;
import com.feedback.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://vamshikumar32501.github.io/student-feedback-frontend")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return authService.sendVerificationCode(request.getEmail());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return authService.resetPassword(request);
    }

    // New GET method to fetch all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    // New GET method to fetch user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return authService.getUserById(id);
    }

    // PUT: Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return authService.updateUser(id, updatedUser);
    }

    // PATCH: Partially update user by ID
    @PatchMapping("/patch/{id}")
    public ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return authService.patchUser(id, updates);
    }

    // HEAD: Check if user exists by ID
    @RequestMapping(value = "/head/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headUser(@PathVariable Long id) {
        return authService.headUser(id);
    }

    // OPTIONS: Show allowed HTTP methods
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsUser() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS)
                .build();
    }

    // DELETE specific user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return authService.deleteUser(id);
    }
    
    // DELETE all users
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllUsers() {
        return authService.deleteAllUsers();
    }
}

