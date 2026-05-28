package com.example.helloapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

@Service
public class AnalyticsService {

    @Autowired
    private ResumeAnalysisRepository repository;

    public long getTotalUploads() {
        return repository.count();
    }

    public double getAverageScore() {

        List<ResumeAnalysis> analyses = repository.findAll();

        if (analyses.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (ResumeAnalysis analysis : analyses) {
            total += analysis.getResumeScore();
        }

        return total / analyses.size();
    }

    public String getMostCommonSkill() {

        Map<String, Integer> skillCount = getSkillFrequency();

        return skillCount
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No skills found");
    }

    public Map<String, Integer> getSkillFrequency() {

        List<ResumeAnalysis> analyses = repository.findAll();

        Map<String, Integer> frequency = new HashMap<>();

        for (ResumeAnalysis analysis : analyses) {

            if (analysis.getSkills() == null) {
                continue;
            }

            String cleanedSkills = analysis.getSkills()
                    .replace("[", "")
                    .replace("]", "");

            String[] skills = cleanedSkills.split(",");

            for (String skill : skills) {

                String trimmedSkill = skill.trim();

                if (trimmedSkill.isEmpty()) {
                    continue;
                }

                frequency.put(
                        trimmedSkill,
                        frequency.getOrDefault(
                                trimmedSkill,
                                0) + 1);
            }
        }

        return frequency;
    }
}