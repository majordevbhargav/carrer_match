package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

@RestController
@RequestMapping("/api/analysis")
public class ResumeAnalysisController {

    @Autowired
    ResumeAnalysisRepository
    resumeAnalysisRepository;

    @GetMapping("/{email}")
    public List<ResumeAnalysis>
    getHistory(
            @PathVariable
            String email){

        return resumeAnalysisRepository
                .findByEmail(
                        email
                );
    }
}