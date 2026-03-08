package com.example.unibridge.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.unibridge.backend.model.UserDetails;
import com.example.unibridge.backend.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class UserController {

    @Autowired
    private UserDetailsRepository repo;
@GetMapping
public List<UserDetails> getAllUsers(){
    return repo.findAll();
}

    @GetMapping("/{userId}")
    public UserDetails getProfile(@PathVariable Long userId){
        return repo.findByUserId(userId);
    }
}