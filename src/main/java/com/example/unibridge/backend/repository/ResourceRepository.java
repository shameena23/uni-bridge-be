package com.example.unibridge.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unibridge.backend.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    long countByUploadedBy(Long userId);
    Optional<Resource> findTopByOrderByRatingDesc();
    List<Resource> findTop10ByOrderByUploadDateDesc();
    List<Resource> findAllByUploadedBy(Long uploadedBy);
}