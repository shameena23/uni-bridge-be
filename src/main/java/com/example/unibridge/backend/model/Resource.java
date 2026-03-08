package com.example.unibridge.backend.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subject;
    private String category;

    @Column(columnDefinition="TEXT")
    private String description;

    private String filePath;
    private Long uploadedBy;
    private Integer rating;

    private LocalDateTime uploadDate;

    private int ratingSum;
    private int ratingCount;
    private double ratingAvg;

    public Resource(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public String getSubject(){return subject;}
    public void setSubject(String subject){this.subject=subject;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}

    public String getFilePath(){return filePath;}
    public void setFilePath(String filePath){this.filePath=filePath;}

    public Long getUploadedBy(){return uploadedBy;}
    public void setUploadedBy(Long uploadedBy){this.uploadedBy=uploadedBy;}

    public Integer getRating(){return rating;}
    public void setRating(Integer rating){this.rating=rating;}

    public LocalDateTime getUploadDate(){return uploadDate;}
    public void setUploadDate(LocalDateTime uploadDate){this.uploadDate=uploadDate;}



    public int getRatingSum() {
    return ratingSum;
}

public int getRatingCount() {
    return ratingCount;
}

public double getRatingAvg() {
    return ratingAvg;
}


public void setRatingSum(int ratingSum) {
    this.ratingSum = ratingSum;
}

public void setRatingCount(int ratingCount) {
    this.ratingCount = ratingCount;
}

public void setRatingAvg(double ratingAvg) {
    this.ratingAvg = ratingAvg;
}
}