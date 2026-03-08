package com.example.unibridge.backend.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.unibridge.backend.model.Resource;
import com.example.unibridge.backend.repository.ResourceRepository;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

    @Autowired
    private ResourceRepository repo;

    // Update this path to your server's absolute path where files are stored
    private final String FILE_BASE_PATH = "/home/rajesh/Desktop/Desktop/unibridge/unibridge/uploads/data/";

    @GetMapping
public List<Map<String, Object>> getResources() {
    List<Resource> resources = repo.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    // Absolute path to your uploads folder
    

    for (Resource r : resources) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("id", r.getId());
        resMap.put("title", r.getTitle());
        resMap.put("description", r.getDescription());
        resMap.put("category", r.getCategory());
        resMap.put("filePath", r.getFilePath());
        resMap.put("uploadedBy", r.getUploadedBy());
        resMap.put("uploadDate", r.getUploadDate());
        resMap.put("rating", r.getRating());
        // fileType from path
        String path = r.getFilePath();
        String fileType = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1).toLowerCase() : "unknown";
        resMap.put("fileType", fileType);

        // fileSize in MB
        File file = new File(FILE_BASE_PATH + path);
        if (file.exists()) {
            double sizeMB = file.length() / (1024.0 * 1024.0);
            sizeMB = Math.round(sizeMB * 100.0) / 100.0;
            resMap.put("fileSizeMB", sizeMB);
        } else {
            resMap.put("fileSizeMB", 0.0);
        }

        response.add(resMap);
    }
        System.out.println("i am here");
        System.out.println(response);
    return response;
}
@PostMapping("/{id}/rate")
public Resource rateResource(@PathVariable Long id, @RequestBody Map<String,Integer> body){

    int rating = body.get("rating");

    Resource resource = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Resource not found"));

    resource.setRatingSum(resource.getRatingSum() + rating);
    resource.setRatingCount(resource.getRatingCount() + 1);

    double avg = (double) resource.getRatingSum() / resource.getRatingCount();
    resource.setRatingAvg(avg);

    // set rating column = floor of ratingAvg
    resource.setRating((int) Math.floor(avg));

    return repo.save(resource);
}
@GetMapping("/resourcedetails/{id}")
public Map<String, Object> getResourceById(@PathVariable Long id) {
    // Fetch resource by ID
    Resource r = repo.findById(id).orElse(null);

    if (r == null) {
        // Return empty map or throw exception if not found
        return Map.of("error", "Resource not found");
    }

    Map<String, Object> resMap = new HashMap<>();
    resMap.put("id", r.getId());
    resMap.put("title", r.getTitle());
    resMap.put("subject", r.getSubject());
    resMap.put("description", r.getDescription());
    resMap.put("category", r.getCategory());
    resMap.put("filePath", r.getFilePath());
    resMap.put("uploadedBy", r.getUploadedBy());
    resMap.put("uploadDate", r.getUploadDate());
    resMap.put("rating", r.getRating());
    // fileType from path
    String path = r.getFilePath();
    String fileType = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1).toLowerCase() : "unknown";
    resMap.put("fileType", fileType);

    // fileSize in MB
    File file = new File(FILE_BASE_PATH + path);
    if (file.exists()) {
        double sizeMB = file.length() / (1024.0 * 1024.0);
        sizeMB = Math.round(sizeMB * 100.0) / 100.0;
        resMap.put("fileSizeMB", sizeMB);
        System.out.println(sizeMB);
    } else {
        resMap.put("fileSizeMB", 0.0);
        
    }
    

    return resMap;
}
@GetMapping("/files/{filename:.+}")
public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) {
    try {

        Path file = Paths.get("/home/rajesh/Desktop/Desktop/unibridge/unibridge/uploads/data")
                .resolve(filename)
                .normalize();

        org.springframework.core.io.Resource resource = new UrlResource(file.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}
    @PostMapping("/upload")
    public Resource upload(@RequestBody Resource resource) {
        // Here you can also compute type and size if needed when saving
        return repo.save(resource);
    }
    @GetMapping("/upload-count/{userId}")
public long getUploadCount(@PathVariable Long userId){
    return repo.countByUploadedBy(userId);
}
}