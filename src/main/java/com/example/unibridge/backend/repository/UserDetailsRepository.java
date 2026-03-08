package com.example.unibridge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unibridge.backend.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByUserId(Long userId);

}