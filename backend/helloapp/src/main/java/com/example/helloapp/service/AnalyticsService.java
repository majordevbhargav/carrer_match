package com.example.helloapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.repository.ResumeAnalysisRepository;

@Service
public class AnalyticsService {

    @Autowired
    ResumeAnalysisRepository repository;

    public long getTotalUploads(){

        return repository.count();
    }
}