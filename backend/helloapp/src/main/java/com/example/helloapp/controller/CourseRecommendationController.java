package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.dto.CourseRecommendationRequest;
import com.example.helloapp.dto.CourseRecommendationResponse;
import com.example.helloapp.service.CourseRecommendationEngineService;

@RestController
@RequestMapping("/api/courses")
public class CourseRecommendationController {

    @Autowired
    private CourseRecommendationEngineService service;

    @PostMapping("/recommend")
    public List<CourseRecommendationResponse> recommendCourses(
            @RequestBody CourseRecommendationRequest request) {

        return service.recommendCourses(
                request.getMissingSkills()
        );
    }
}