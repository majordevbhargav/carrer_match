package com.example.helloapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.helloapp.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins="http://localhost:3000")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file")
            MultipartFile file){

        return resumeService
                .uploadResume(file);
    }
}