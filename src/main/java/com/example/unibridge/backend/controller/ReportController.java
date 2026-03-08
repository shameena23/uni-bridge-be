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

import com.example.unibridge.backend.model.Report;
import com.example.unibridge.backend.repository.ReportRepository;
@RestController
@RequestMapping("/api/report")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportRepository reportRepo;

    @PostMapping
    public Report createReport(@RequestBody Report report) {
       
        return reportRepo.save(report);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }

    @GetMapping("/count/{resourceId}")
public long getReportCount(@PathVariable Long resourceId){
    return reportRepo.countByResourceId(resourceId);
}

@GetMapping("/user-reports/{userId}")
public long getReportsForUserResources(@PathVariable Long userId){
    return reportRepo.countReportsForUserResources(userId);
}

}