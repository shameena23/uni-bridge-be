package com.example.unibridge.backend.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.unibridge.backend.model.User;
import com.example.unibridge.backend.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/login")
    public Object login(@RequestBody Map<String,String> data){

        String name = data.get("name");
        String password = data.get("password");

        // Debug (optional but useful)
        System.out.println("Login attempt: " + name);

        Optional<User> user = repo.findByNameIgnoreCase(name);

        if(user.isPresent()){

            User u = user.get();

            if(u.getPassword().equals(password)){
                return u;
            } 
            else{
                return "Wrong password";
            }

        }

        return "User not found";
    }
}