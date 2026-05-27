package com.example.helloapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String jobMatches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email) {

        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(
            String skills) {

        this.skills = skills;
    }

    public String getJobMatches() {
        return jobMatches;
    }

    public void setJobMatches(
            String jobMatches) {

        this.jobMatches = jobMatches;
    }

    private String resumeName;

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(
            String resumeName) {

        this.resumeName = resumeName;
    }

    private LocalDateTime uploadDate;

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(
            LocalDateTime uploadDate) {

        this.uploadDate = uploadDate;
    }
}