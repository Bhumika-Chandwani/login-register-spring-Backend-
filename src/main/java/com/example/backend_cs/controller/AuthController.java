package com.example.backend_cs.controller;

import com.example.backend_cs.model.User;
import com.example.backend_cs.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    
    @Autowired
    private UserService userService;
    

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        userService.registerUser(user);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }
    

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> loginRequest,
            HttpSession session,
            HttpServletResponse response) {
        
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        // Authenticate user
        User user = userService.loginUser(username, password);
        
        // Store user info in SESSION
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        
        // Create COOKIE
        Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setMaxAge(30 * 60); // 30 minutes
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        // Send response
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Login successful");
        responseMap.put("username", user.getUsername());
        return ResponseEntity.ok(responseMap);
    }
    

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            HttpSession session,
            HttpServletResponse response) {
        
        // Destroy session
        session.invalidate();
        
        // Delete cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Logout successful");
        return ResponseEntity.ok(responseMap);
    }
    

    @GetMapping("/check-session")
    public ResponseEntity<Map<String, Object>> checkSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        
        Map<String, Object> response = new HashMap<>();
        if (username != null) {
            response.put("authenticated", true);
            response.put("username", username);
        } else {
            response.put("authenticated", false);
        }
        return ResponseEntity.ok(response);
    }
}

// EXPLANATION:
// @RestController - Makes this a REST API controller
// @RequestMapping("/api/auth") - Base URL path
// @CrossOrigin - Allows React (port 3000) to call these APIs
//   allowCredentials="true" enables cookies/sessions
//
// SESSIONS:
//   - Stored on server (in memory)
//   - Used to track logged-in users
//   - Automatically managed by Spring Boot
//
// COOKIES:
//   - Stored in browser
//   - Contains username
//   - HttpOnly=true prevents JavaScript access (security)
//   - Expires after 30 minutes