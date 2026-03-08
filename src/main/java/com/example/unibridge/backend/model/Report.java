package com.example.unibridge.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String description;

    @Column(name = "reported_by")
    private Long reportedBy;

    @Column(name = "resource_id")
    private Long resourceId;

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Long getReportedBy() {
        return reportedBy;
    }

    public Long getResourceId() {
        return resourceId;
    }
}