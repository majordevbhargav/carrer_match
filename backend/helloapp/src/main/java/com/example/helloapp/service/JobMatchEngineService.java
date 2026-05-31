package com.example.helloapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.dto.JobMatchResponse;
import com.example.helloapp.entity.JobPost;
import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.JobPostRepository;

@Service
public class JobMatchEngineService {

    @Autowired
    private ResumeRecommendationService resumeRecommendationService;

    @Autowired
    private JobPostRepository jobPostRepository;

    public List<JobMatchResponse> getRecommendedJobs(String email) {

        ResumeAnalysis bestResume =
                resumeRecommendationService.getBestResume(email);

        if (bestResume == null) {
            return new ArrayList<>();
        }

        List<String> resumeSkills =
                parseSkills(bestResume.getSkills());

        List<JobPost> jobs =
                jobPostRepository.findAll();

        List<JobMatchResponse> responses =
                new ArrayList<>();

        for (JobPost job : jobs) {

            List<String> jobSkills =
                    parseSkills(job.getSkills());

            List<String> matchedSkills =
                    new ArrayList<>();

            List<String> missingSkills =
                    new ArrayList<>();

            for (String skill : jobSkills) {
                if (containsIgnoreCase(resumeSkills, skill)) {
                    matchedSkills.add(skill);
                } else {
                    missingSkills.add(skill);
                }
            }

            int matchScore = 0;

            if (!jobSkills.isEmpty()) {
                matchScore =
                        (int) Math.round(
                                ((double) matchedSkills.size()
                                        / jobSkills.size()) * 100
                        );
            }

            responses.add(
                    new JobMatchResponse(
                            job.getId(),
                            bestResume.getResumeName(),
                            job.getTitle(),
                            job.getCompany(),
                            job.getLocation(),
                            job.getType(),
                            job.getApplyLink(),
                            matchScore,
                            matchedSkills,
                            missingSkills
                    )
            );
        }

        responses.sort(
                Comparator.comparingInt(
                        JobMatchResponse::getMatchScore
                ).reversed()
        );

        return responses;
    }

    private List<String> parseSkills(String skillsText) {

        if (skillsText == null || skillsText.isBlank()) {
            return new ArrayList<>();
        }

        String cleaned =
                skillsText
                        .replace("[", "")
                        .replace("]", "");

        return Arrays.stream(cleaned.split(","))
                .map(String::trim)
                .filter(skill -> !skill.isEmpty())
                .toList();
    }

    private boolean containsIgnoreCase(
            List<String> list,
            String value) {

        return list.stream()
                .anyMatch(
                        item -> item.equalsIgnoreCase(value)
                );
    }
}