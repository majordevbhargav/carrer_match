package com.example.helloapp.dto;

import java.util.List;
import java.util.Map;

public class ResumeResponse {

    private List<String> skills;

    private Map<String, Integer> jobMatches;

    private Map<String, List<String>> missingSkills;

    private Map<String, List<String>> recommendedCourses;

    private List<String> topJobs;

    private int resumeScore;

    // Skills
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(
            List<String> skills) {

        this.skills = skills;
    }

    // Job Matches
    public Map<String, Integer> getJobMatches() {
        return jobMatches;
    }

    public void setJobMatches(
            Map<String, Integer> jobMatches) {

        this.jobMatches = jobMatches;
    }

    // Missing Skills
    public Map<String, List<String>> getMissingSkills() {

        return missingSkills;
    }

    public void setMissingSkills(
            Map<String, List<String>> missingSkills) {

        this.missingSkills = missingSkills;
    }

    // Courses
    public Map<String, List<String>> getRecommendedCourses() {

        return recommendedCourses;
    }

    public void setRecommendedCourses(
            Map<String, List<String>> recommendedCourses) {

        this.recommendedCourses = recommendedCourses;
    }

    // Top Jobs
    public List<String> getTopJobs() {
        return topJobs;
    }

    public void setTopJobs(
            List<String> topJobs) {

        this.topJobs = topJobs;
    }

    // Resume Score
    public int getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(
            int resumeScore) {

        this.resumeScore = resumeScore;
    }

    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}