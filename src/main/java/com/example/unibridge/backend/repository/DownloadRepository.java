package com.example.unibridge.backend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unibridge.backend.model.Download;

public interface DownloadRepository extends JpaRepository<Download, Long> {

    long countByDownloadedby(Long downloadedby);
    long countByResourceid(Long resourceid);
    List<Download> findByDownloadedby(Long downloadedby);


}