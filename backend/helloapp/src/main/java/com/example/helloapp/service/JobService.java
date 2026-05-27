package com.example.helloapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.entity.Job;
import com.example.helloapp.repository.JobRepository;

@Service
public class JobService {

        @Autowired
        JobRepository jobRepository;

        // Match user skills with jobs
        public Map<String, Integer> matchJobs(
                        List<String> userSkills) {

                List<Job> jobs = jobRepository.findAll();

                Map<String, Integer> result = new HashMap<>();

                for (Job job : jobs) {

                        String[] requiredSkills = job.getSkills().split(",");

                        int matched = 0;

                        for (String skill : requiredSkills) {

                                if (userSkills.contains(
                                                skill.trim())) {

                                        matched++;
                                }
                        }

                        int percentage = (matched * 100)
                                        /
                                        requiredSkills.length;

                        result.put(
                                        job.getTitle(),
                                        percentage);
                }

                return result;
        }

        // Find missing skills
        public Map<String, List<String>> findMissingSkills(
                        List<String> userSkills) {

                List<Job> jobs = jobRepository.findAll();

                Map<String, List<String>> missingSkillsMap = new HashMap<>();

                for (Job job : jobs) {

                        String[] requiredSkills = job.getSkills()
                                        .split(",");

                        List<String> missing = new ArrayList<>();

                        for (String skill : requiredSkills) {

                                String trimmedSkill = skill.trim();

                                if (!userSkills.contains(
                                                trimmedSkill)) {

                                        missing.add(
                                                        trimmedSkill);
                                }
                        }

                        missingSkillsMap.put(
                                        job.getTitle(),
                                        missing);
                }

                return missingSkillsMap;
        }

        // Recommend courses
        public Map<String, List<String>> recommendCourses(
                        Map<String, List<String>> missingSkills) {

                Map<String, String> courseMap = new HashMap<>();

                courseMap.put(
                                "Python",
                                "Learn Python Basics");

                courseMap.put(
                                "Docker",
                                "Docker for Beginners");

                courseMap.put(
                                "Machine Learning",
                                "ML Foundations");

                courseMap.put(
                                "Spring Boot",
                                "Spring Boot Masterclass");

                courseMap.put(
                                "AWS",
                                "AWS Cloud Essentials");

                Map<String, List<String>> recommendations = new HashMap<>();

                for (String job : missingSkills.keySet()) {

                        List<String> courses = new ArrayList<>();

                        for (String skill : missingSkills.get(job)) {

                                if (courseMap
                                                .containsKey(skill)) {

                                        courses.add(
                                                        courseMap.get(skill));
                                }
                        }

                        recommendations.put(
                                        job,
                                        courses);
                }

                return recommendations;
        }

        // Resume Score
        public int calculateResumeScore(List<String> skills) {

                if (skills == null || skills.isEmpty()) {
                        return 20; // never return absolute zero
                }

                int score = 0;

                // Core skills weight
                List<String> importantSkills = List.of(
                                "Java",
                                "Spring Boot",
                                "React",
                                "Node.js",
                                "Python",
                                "MongoDB",
                                "SQL",
                                "Git",
                                "Docker",
                                "AWS",
                                "Machine Learning");

                for (String skill : skills) {

                        if (importantSkills.contains(skill)) {
                                score += 8;
                        } else {
                                score += 3;
                        }
                }

                // bonus points
                if (skills.contains("Java") &&
                                skills.contains("Spring Boot")) {

                        score += 10;
                }

                if (skills.contains("React") &&
                                skills.contains("JavaScript")) {

                        score += 10;
                }

                // keep score within range
                if (score > 100) {
                        score = 100;
                }

                // minimum reasonable score
                if (score < 35) {
                        score = 35;
                }

                return score;
        }

        public List<String> getTopJobs(
                        Map<String, Integer> matches) {

                return matches
                                .entrySet()
                                .stream()

                                .filter(
                                                entry -> entry.getValue() > 0)

                                .sorted(
                                                (a, b) -> b.getValue()
                                                                .compareTo(
                                                                                a.getValue()))

                                .limit(3)

                                .map(
                                                entry -> entry.getKey())

                                .toList();
        }

        public String generateFeedback(
                        List<String> skills,
                        int score) {

                String feedback = "";

                if (score >= 80) {

                        feedback = "Strong resume. Good technical coverage with industry-relevant skills.";
                }

                else if (score >= 50) {

                        feedback = "Average resume. Add more technical skills and practical projects.";
                }

                else {

                        feedback = "Resume needs improvement. Add projects, certifications and technical skills.";
                }

                if (!skills.contains("Python")) {

                        feedback += " Consider learning Python.";
                }

                if (!skills.contains("Docker")) {

                        feedback += " Add Docker knowledge.";
                }

                if (!skills.contains("Machine Learning")) {

                        feedback += " Add AI/ML skills.";
                }

                return feedback;
        }
}