package com.example.unibridge.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unibridge.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameIgnoreCase(String name);



}