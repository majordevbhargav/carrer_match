package com.example.helloapp.dto;

import java.util.List;

public class JobMatchResponse {

    private Long jobId;
    private String resumeName;
    private String jobTitle;
    private String company;
    private String location;
    private String type;
    private String applyLink;
    private int matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public JobMatchResponse(
            Long jobId,
            String resumeName,
            String jobTitle,
            String company,
            String location,
            String type,
            String applyLink,
            int matchScore,
            List<String> matchedSkills,
            List<String> missingSkills) {

        this.jobId = jobId;
        this.resumeName = resumeName;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.type = type;
        this.applyLink = applyLink;
        this.matchScore = matchScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public Long getJobId() { return jobId; }
    public String getResumeName() { return resumeName; }
    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public String getApplyLink() { return applyLink; }
    public int getMatchScore() { return matchScore; }
    public List<String> getMatchedSkills() { return matchedSkills; }
    public List<String> getMissingSkills() { return missingSkills; }
}