package com.example.unibridge.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.unibridge.backend.model.Download;
import com.example.unibridge.backend.repository.DownloadRepository;

@RestController
@RequestMapping("/api/download")
@CrossOrigin("*")
public class DownloadController {

    @Autowired
    private DownloadRepository repo;

    @PostMapping
    public Download saveDownload(@RequestBody Download download){
        return repo.save(download);
    }
    @GetMapping("/count/{userId}")
public long getDownloadCount(@PathVariable Long userId){
    return repo.countByDownloadedby(userId);
}
@GetMapping("/resource/count/{resourceId}")
public long getResourceDownloadCount(@PathVariable Long resourceId){
    return repo.countByResourceid(resourceId);
}
@GetMapping("/user/{userId}")
    public List<Download> getDownloadsByUser(@PathVariable Long userId){
        return repo.findByDownloadedby(userId);
    }
}