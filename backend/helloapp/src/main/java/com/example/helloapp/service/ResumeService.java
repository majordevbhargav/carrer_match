package com.example.helloapp.service;

import com.example.helloapp.dto.ResumeResponse;
import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

        @Autowired
        private JobService jobService;

        @Autowired
        private ResumeAnalysisRepository resumeAnalysisRepository;

        private final String UPLOAD_DIR = Paths.get(
                        "backend",
                        "helloapp",
                        "uploads")
                        .toAbsolutePath()
                        .toString();

        public ResumeResponse uploadResume(
                        MultipartFile file) {

                try {

                        File directory = new File(
                                        UPLOAD_DIR);

                        if (!directory.exists()) {

                                directory.mkdirs();
                        }

                        String fileName = file.getOriginalFilename();

                        File destination = new File(
                                        directory,
                                        fileName);

                        System.out.println(
                                        "Saving to: "
                                                        + destination.getAbsolutePath());

                        file.transferTo(
                                        destination);

                        String extractedText = extractText(
                                        destination
                                                        .getAbsolutePath());

                        List<String> skills = extractSkills(
                                        extractedText);

                        System.out.println(
                                        "Detected Skills:");

                        System.out.println(
                                        skills);

                        Map<String, Integer> matches = jobService.matchJobs(
                                        skills);

                        List<String> topJobs = jobService.getTopJobs(
                                        matches);

                        int score = jobService
                                        .calculateResumeScore(
                                                        skills);
                        String feedback = jobService.generateFeedback(
                                        skills,
                                        score);

                        Map<String, List<String>> missingSkills = jobService
                                        .findMissingSkills(
                                                        skills);

                        Map<String, List<String>> courses = jobService
                                        .recommendCourses(
                                                        missingSkills);

                        ResumeResponse response = new ResumeResponse();

                        response.setSkills(
                                        skills);

                        response.setJobMatches(
                                        matches);

                        response.setMissingSkills(
                                        missingSkills);

                        response.setRecommendedCourses(
                                        courses);

                        response.setTopJobs(
                                        topJobs);

                        response.setResumeScore(
                                        score);

                        ResumeAnalysis analysis = new ResumeAnalysis();

                        analysis.setEmail(
                                        "currentUser@gmail.com");

                        analysis.setResumeName(
                                        fileName);

                        analysis.setUploadDate(
                                        LocalDateTime.now());

                        analysis.setSkills(
                                        skills.toString());

                        analysis.setJobMatches(
                                        matches.toString());

                        System.out.println(
                                        "Saving analysis for: "
                                                        + fileName);
                        analysis.setResumeScore(score);

                        resumeAnalysisRepository
                                        .save(
                                                        analysis);

                        response.setFeedback(
                                        feedback);

                        return response;

                }

                catch (IOException e) {

                        e.printStackTrace();

                        ResumeResponse response = new ResumeResponse();

                        response.setSkills(
                                        new ArrayList<>());

                        response.setJobMatches(
                                        new HashMap<>());

                        response.setMissingSkills(
                                        new HashMap<>());

                        response.setRecommendedCourses(
                                        new HashMap<>());

                        response.setTopJobs(
                                        new ArrayList<>());
                        response.setResumeScore(
                                        0);
                        response.setFeedback(
                                        "Could not analyze resume.");

                        return response;
                }
        }

        public String extractText(
                        String filePath) {

                try {

                        File file = new File(filePath);

                        PDDocument document = Loader.loadPDF(
                                        file);

                        PDFTextStripper stripper = new PDFTextStripper();

                        String text = stripper.getText(
                                        document);

                        document.close();

                        return text;
                }

                catch (Exception e) {

                        e.printStackTrace();

                        return "Could not extract text";
                }
        }

        public List<String> extractSkills(
                        String text) {

                List<String> allSkills = List.of(

                                "Java",
                                "Spring Boot",
                                "React",
                                "Node.js",
                                "Python",
                                "MongoDB",
                                "PostgreSQL",
                                "HTML",
                                "CSS",
                                "JavaScript",
                                "TypeScript",
                                "AWS",
                                "Docker",
                                "Git",
                                "Machine Learning",
                                "SQL");

                List<String> foundSkills = new ArrayList<>();

                for (String skill : allSkills) {

                        if (text.toLowerCase()
                                        .contains(
                                                        skill.toLowerCase())) {

                                foundSkills.add(
                                                skill);
                        }
                }

                return foundSkills;
        }
}