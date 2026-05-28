package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.entity.JobPost;
import com.example.helloapp.repository.JobPostRepository;

@RestController
@RequestMapping("/api/jobs")
public class JobPostController {

    @Autowired
    private JobPostRepository jobPostRepository;

    @GetMapping
    public List<JobPost> getAllJobs() {

        return jobPostRepository.findAll();
    }

    @PostMapping
    public JobPost addJob(
            @RequestBody JobPost jobPost) {

        return jobPostRepository.save(
                jobPost
        );
    }

    @GetMapping("/search")
    public List<JobPost> searchJobs(
            @RequestParam String keyword) {

        return jobPostRepository
                .findByTitleContainingIgnoreCase(
                        keyword
                );
    }
}