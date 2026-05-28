package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.entity.Application;
import com.example.helloapp.repository.ApplicationRepository;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/save")
    public Application saveApplication(
            @RequestBody Application application) {

        application.setStatus("Saved");

        return applicationRepository.save(application);
    }

    @GetMapping("/{email}")
    public List<Application> getApplications(
            @PathVariable String email) {

        return applicationRepository.findByEmail(email);
    }
}