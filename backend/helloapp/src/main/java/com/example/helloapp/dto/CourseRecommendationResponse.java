package com.example.helloapp.dto;

public class CourseRecommendationResponse {

    private String skill;
    private String courseTitle;
    private String platform;
    private String url;

    public CourseRecommendationResponse(
            String skill,
            String courseTitle,
            String platform,
            String url) {

        this.skill = skill;
        this.courseTitle = courseTitle;
        this.platform = platform;
        this.url = url;
    }

    public String getSkill() {
        return skill;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getPlatform() {
        return platform;
    }

    public String getUrl() {
        return url;
    }
}
