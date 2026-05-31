package com.example.helloapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.helloapp.entity.ResumeAnalysis;
import com.example.helloapp.repository.ResumeAnalysisRepository;

@Service
public class AiCareerService {

    @Autowired
    private ResumeAnalysisRepository resumeAnalysisRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    private static final String OPENAI_URL =
            "https://api.openai.com/v1/chat/completions";

    public String chatWithCareerAi(
            String email,
            String question) {

        List<ResumeAnalysis> resumes =
                resumeAnalysisRepository.findByEmail(email);

        ResumeAnalysis latestResume =
                resumes.isEmpty()
                        ? null
                        : resumes.get(resumes.size() - 1);

        String context =
                latestResume == null
                        ? "No resume data found."
                        : """
                        Resume Name: %s
                        Resume Score: %s
                        Skills: %s
                        Job Matches: %s
                        Upload Date: %s
                        """.formatted(
                                latestResume.getResumeName(),
                                latestResume.getResumeScore(),
                                latestResume.getSkills(),
                                latestResume.getJobMatches(),
                                latestResume.getUploadDate()
                        );

        String prompt =
                """
                You are Career Match AI, a practical career mentor for students.

                Use the resume data below and answer the user's question.
                Rules:
                - Keep it practical.
                - Do not invent fake experience.
                - Suggest jobs, missing skills, projects, and learning direction.
                - Keep answer under 180 words.

                Resume Data:
                %s

                User Question:
                %s
                """.formatted(context, question);

        return callOpenAI(
                prompt,
                "Based on your resume, you are best suited for Java Backend Developer and Frontend Developer roles. Next, learn Docker, Kubernetes, Linux, and Python to improve Cloud and AI role readiness."
        );
    }

    public String rewriteResumeText(
            String text,
            String targetRole) {

        String prompt =
                """
                You are an ATS resume rewriting assistant.

                Rewrite the text below for the target role: %s

                Rules:
                - Return 3 ATS-friendly bullet points.
                - Use strong action verbs.
                - Keep it truthful.
                - Do not invent fake company names.
                - Add measurable impact only when logically safe.

                Original Text:
                %s
                """.formatted(targetRole, text);

        return callOpenAI(
                prompt,
                """
                - Developed RESTful APIs using Java and Spring Boot to support scalable backend workflows.
                - Integrated PostgreSQL database operations for efficient data storage, retrieval, and management.
                - Built responsive frontend components using React to improve user interaction and application usability.
                """
        );
    }

    private String callOpenAI(
            String prompt,
            String fallback) {

        try {
            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.setBearerAuth(
                    openAiApiKey
            );

            Map<String, Object> message =
                    Map.of(
                            "role",
                            "user",
                            "content",
                            prompt
                    );

            Map<String, Object> body =
                    Map.of(
                            "model",
                            model,
                            "messages",
                            List.of(message),
                            "temperature",
                            0.4
                    );

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(
                            OPENAI_URL,
                            request,
                            Map.class
                    );

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) response
                            .getBody()
                            .get("choices");

            Map<String, Object> firstChoice =
                    choices.get(0);

            Map<String, Object> responseMessage =
                    (Map<String, Object>) firstChoice.get("message");

            return responseMessage
                    .get("content")
                    .toString();

        } catch (Exception error) {

            System.out.println(
                    "AI fallback used: "
                            + error.getMessage()
            );

            return fallback;
        }
    }
}