package com.example.helloapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.helloapp.dto.CourseRecommendationResponse;

@Service
public class CourseRecommendationEngineService {

    private final Map<String, CourseRecommendationResponse> courseMap =
            Map.ofEntries(
                    Map.entry(
                            "Docker",
                            new CourseRecommendationResponse(
                                    "Docker",
                                    "Docker for Beginners",
                                    "Udemy",
                                    "https://www.udemy.com/course/docker-mastery/"
                            )
                    ),
                    Map.entry(
                            "Kubernetes",
                            new CourseRecommendationResponse(
                                    "Kubernetes",
                                    "Kubernetes Fundamentals",
                                    "Coursera",
                                    "https://www.coursera.org/learn/google-kubernetes-engine"
                            )
                    ),
                    Map.entry(
                            "Linux",
                            new CourseRecommendationResponse(
                                    "Linux",
                                    "Linux Command Line Basics",
                                    "Coursera",
                                    "https://www.coursera.org/learn/linux-command-line-basics"
                            )
                    ),
                    Map.entry(
                            "Python",
                            new CourseRecommendationResponse(
                                    "Python",
                                    "Python for Everybody",
                                    "Coursera",
                                    "https://www.coursera.org/specializations/python"
                            )
                    ),
                    Map.entry(
                            "Machine Learning",
                            new CourseRecommendationResponse(
                                    "Machine Learning",
                                    "Machine Learning Specialization",
                                    "Coursera",
                                    "https://www.coursera.org/specializations/machine-learning-introduction"
                            )
                    ),
                    Map.entry(
                            "AWS",
                            new CourseRecommendationResponse(
                                    "AWS",
                                    "AWS Cloud Practitioner Essentials",
                                    "AWS Skill Builder",
                                    "https://skillbuilder.aws/"
                            )
                    ),
                    Map.entry(
                            "Spring Boot",
                            new CourseRecommendationResponse(
                                    "Spring Boot",
                                    "Spring Boot 3 and Spring Framework 6",
                                    "Udemy",
                                    "https://www.udemy.com/course/spring-hibernate-tutorial/"
                            )
                    ),
                    Map.entry(
                            "TypeScript",
                            new CourseRecommendationResponse(
                                    "TypeScript",
                                    "Understanding TypeScript",
                                    "Udemy",
                                    "https://www.udemy.com/course/understanding-typescript/"
                            )
                    )
            );

    public List<CourseRecommendationResponse> recommendCourses(
            List<String> missingSkills) {

        List<CourseRecommendationResponse> recommendations =
                new ArrayList<>();

        if (missingSkills == null || missingSkills.isEmpty()) {
            return recommendations;
        }

        for (String skill : missingSkills) {

            CourseRecommendationResponse course =
                    courseMap.get(skill);

            if (course != null) {
                recommendations.add(course);
            } else {
                recommendations.add(
                        new CourseRecommendationResponse(
                                skill,
                                "Learn " + skill + " Basics",
                                "Recommended Learning Path",
                                "https://www.google.com/search?q=learn+" + skill.replace(" ", "+")
                        )
                );
            }
        }

        return recommendations;
    }
}