package com.example.helloapp.dto;

public class CompareRequest {

    private Long resumeAId;

    private Long resumeBId;

    public Long getResumeAId() {
        return resumeAId;
    }

    public void setResumeAId(Long resumeAId) {
        this.resumeAId = resumeAId;
    }

    public Long getResumeBId() {
        return resumeBId;
    }

    public void setResumeBId(Long resumeBId) {
        this.resumeBId = resumeBId;
    }
}