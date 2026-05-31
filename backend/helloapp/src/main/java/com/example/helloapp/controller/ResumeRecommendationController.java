package com.example.helloapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.service.ResumeRecommendationService;

@RestController
@RequestMapping("/api/recommendation")
public class ResumeRecommendationController {

    @Autowired
    private ResumeRecommendationService service;

    @GetMapping("/{email}")
    public ResumeAnalysis getBestResume(
            @PathVariable String email) {

        return service.getBestResume(
                email
        );
    }
}