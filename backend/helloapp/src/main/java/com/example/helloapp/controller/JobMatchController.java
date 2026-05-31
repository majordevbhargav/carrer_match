package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.dto.JobMatchResponse;
import com.example.helloapp.service.JobMatchEngineService;

@RestController
@RequestMapping("/api/job-match")
public class JobMatchController {

    @Autowired
    private JobMatchEngineService jobMatchEngineService;

    @GetMapping("/{email}")
    public List<JobMatchResponse> getRecommendedJobs(
            @PathVariable String email) {

        return jobMatchEngineService.getRecommendedJobs(email);
    }
}