package com.example.helloapp.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

@Service
public class ResumeRecommendationService {

    @Autowired
    private ResumeAnalysisRepository repository;

    public ResumeAnalysis getBestResume(String email) {

        List<ResumeAnalysis> resumes =
                repository.findByEmail(email);

        return resumes.stream()
                .max(
                        Comparator
                                .comparingInt(ResumeAnalysis::getResumeScore)
                                .thenComparing(
                                        resume -> resume.getUploadDate() == null
                                                ? LocalDateTime.MIN
                                                : resume.getUploadDate()
                                )
                )
                .orElse(null);
    }
}