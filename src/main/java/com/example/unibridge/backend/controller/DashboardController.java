package com.example.unibridge.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.unibridge.backend.model.Resource;
import com.example.unibridge.backend.repository.ResourceRepository;
import com.example.unibridge.backend.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats(@RequestParam(required = false) Long userId) {
        Map<String, Object> stats = new HashMap<>();

        long totalResources = resourceRepository.count();
        long totalUsers = userDetailsRepository.count();
        long myUploads = userId != null ? resourceRepository.countByUploadedBy(userId) : 0;

        Resource topRated = resourceRepository.findTopByOrderByRatingDesc().orElse(null);

        stats.put("totalResources", totalResources);
        stats.put("totalUsers", totalUsers);
        stats.put("myUploads", myUploads);
        stats.put("topRatedResource", topRated);

        return stats;
    }

    @GetMapping("/latest")
    public List<Resource> getLatestResources() {
        return resourceRepository.findTop10ByOrderByUploadDateDesc();
    }
}