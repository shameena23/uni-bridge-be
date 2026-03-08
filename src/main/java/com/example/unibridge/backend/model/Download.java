package com.example.unibridge.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "download")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long downloadedby;

    private Long resourceid;

    public Download() {}

    public Download(Long downloadedby, Long resourceid) {
        this.downloadedby = downloadedby;
        this.resourceid = resourceid;
    }

    public Long getId() {
        return id;
    }

    public Long getDownloadedby() {
        return downloadedby;
    }

    public void setDownloadedby(Long downloadedby) {
        this.downloadedby = downloadedby;
    }

    public Long getResourceid() {
        return resourceid;
    }

    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }
}