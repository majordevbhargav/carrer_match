package com.example.helloapp.dto;

import java.util.List;

public class CourseRecommendationRequest {

    private List<String> missingSkills;

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}