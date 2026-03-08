package com.example.unibridge.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.unibridge.backend.model.UserDetails;
import com.example.unibridge.backend.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class UserController {

    @Autowired
    private UserDetailsRepository repo;

    @GetMapping("/{userId}")
    public UserDetails getProfile(@PathVariable Long userId){
        return repo.findByUserId(userId);
    }
}