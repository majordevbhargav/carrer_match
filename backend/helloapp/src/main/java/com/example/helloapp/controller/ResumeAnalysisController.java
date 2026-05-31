package com.example.helloapp.controller;

import com.example.helloapp.dto.CompareRequest;
import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
public class ResumeAnalysisController {

        @Autowired
        private ResumeAnalysisRepository resumeAnalysisRepository;

        @GetMapping("/{email}")
        public List<ResumeAnalysis> getHistory(
                        @PathVariable String email) {

                return resumeAnalysisRepository.findByEmail(
                                email);
        }

        @PostMapping("/compare")
        public Map<String, Object> compareResumes(
                        @RequestBody CompareRequest request) {

                Optional<ResumeAnalysis> resumeA = resumeAnalysisRepository.findById(
                                request.getResumeAId());

                Optional<ResumeAnalysis> resumeB = resumeAnalysisRepository.findById(
                                request.getResumeBId());

                if (resumeA.isEmpty() || resumeB.isEmpty()) {
                        return Map.of(
                                        "error",
                                        "One or both resumes not found");
                }

                ResumeAnalysis analysisA = resumeA.get();

                ResumeAnalysis analysisB = resumeB.get();

                Set<String> skillsA = parseSkills(
                                analysisA.getSkills());

                Set<String> skillsB = parseSkills(
                                analysisB.getSkills());

                Set<String> commonSkills = new HashSet<>(
                                skillsA);

                commonSkills.retainAll(
                                skillsB);

                Set<String> uniqueToA = new HashSet<>(
                                skillsA);

                uniqueToA.removeAll(
                                skillsB);

                Set<String> uniqueToB = new HashSet<>(
                                skillsB);

                uniqueToB.removeAll(
                                skillsA);

                String betterResume = analysisA.getResumeScore() >= analysisB.getResumeScore()
                                ? analysisA.getResumeName()
                                : analysisB.getResumeName();

                return Map.of(
                                "resumeA",
                                analysisA,

                                "resumeB",
                                analysisB,

                                "commonSkills",
                                commonSkills,

                                "uniqueToA",
                                uniqueToA,

                                "uniqueToB",
                                uniqueToB,

                                "betterResume",
                                betterResume);
        }

        private Set<String> parseSkills(
                        String skillsText) {

                Set<String> skills = new HashSet<>();

                if (skillsText == null || skillsText.isBlank()) {
                        return skills;
                }

                String cleaned = skillsText
                                .replace("[", "")
                                .replace("]", "");

                Arrays.stream(
                                cleaned.split(","))
                                .map(String::trim)
                                .filter(skill -> !skill.isEmpty())
                                .forEach(skills::add);

                return skills;
        }
}