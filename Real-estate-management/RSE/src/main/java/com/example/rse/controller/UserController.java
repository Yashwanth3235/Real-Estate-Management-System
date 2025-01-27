package com.example.rse.controller;

import com.example.rse.model.User;
import com.example.rse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Login Endpoint
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        System.out.println("Login attempt with email: " + user.getEmail().toLowerCase());

        Optional<User> existingUser = userService.findByEmail(user.getEmail().toLowerCase());

        if (existingUser.isPresent()) {
            User foundUser = existingUser.get();
            System.out.println("User found in DB: " + foundUser.getEmail());

            boolean passwordMatches = foundUser.getPassword().equals(user.getPassword());
            boolean roleMatches = foundUser.getRole().equalsIgnoreCase(user.getRole());

            System.out.println("Password match: " + passwordMatches);
            System.out.println("Role match: " + roleMatches);

            if (passwordMatches && roleMatches) {
                Map<String, Object> response = new HashMap<>();
                response.put("email", foundUser.getEmail());
                response.put("role", foundUser.getRole());
                return ResponseEntity.ok(response);
            }
        }

        System.out.println("Invalid username, password, or role.");
        return ResponseEntity.status(401).body("Invalid username, password, or role");
    }
    
    
	  @PostMapping("/register")
	    @CrossOrigin(origins = "http://localhost:3000") 
	    public ResponseEntity<User> register(@Valid @RequestBody User user) {
	        System.out.println("Received registration data: " + user.getUsername() + ", Email: " + user.getEmail());
	        
	        // Additional null check for email
	        if (user.getEmail() == null || user.getEmail().isEmpty()) {
	            System.out.println("Error: Email is null or empty");
	            return ResponseEntity.badRequest().body(null);
	        }

	        try {
	            User savedUser = userService.saveUser(user);
	            return ResponseEntity.ok(savedUser);
	        } catch (Exception e) {
	            System.out.println("Error during registration: " + e.getMessage());
	            return ResponseEntity.status(500).body(null);
	        }
	    }

}
