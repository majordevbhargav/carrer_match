package com.example.helloapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.service.AnalyticsService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/analytics")
    public Map<String, Object> getAnalytics() {

        return Map.of(

                "totalUploads",
                analyticsService.getTotalUploads(),

                "averageScore",
                analyticsService.getAverageScore(),

                "mostCommonSkill",
                analyticsService.getMostCommonSkill(),

                "skillFrequency",
                analyticsService.getSkillFrequency()
        );
    }
}