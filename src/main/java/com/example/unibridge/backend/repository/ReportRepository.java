package com.example.unibridge.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.unibridge.backend.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    long countByResourceId(Long resourceId);

    @Query("SELECT COUNT(r) FROM Report r JOIN Resource res ON r.resourceId = res.id WHERE res.uploadedBy = :userId")
long countReportsForUserResources(@Param("userId") Long userId);
}