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
public Map<String, Object> logins(@RequestBody Map<String,String> data) {

    String email = data.get("email");
    String password = data.get("password");

    String studentPattern = "^[a-z]+\\.[0-9]+@[a-z]+\\.ritchennai\\.edu\\.in$";
    String staffPattern = "^[a-zA-Z]+@ritchennai\\.edu\\.in$";

    if(!(email.matches(studentPattern) || email.matches(staffPattern))){
        return Map.of("error","Invalid Email Format");
    }

    Optional<User> userOptional = repo.findByEmail(email);

    if(userOptional.isEmpty()){
        return Map.of("error","User not found");
    }

    User user = userOptional.get();

    if(!user.getPassword().equals(password)){
        return Map.of("error","Incorrect Password");
    }

    return Map.of(
        "id", user.getId(),
        "role", user.getRole()
    );
}
}
