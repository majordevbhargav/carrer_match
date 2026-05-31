package com.example.helloapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.dto.AiChatRequest;
import com.example.helloapp.dto.AiRewriteRequest;
import com.example.helloapp.service.AiCareerService;

@RestController
@RequestMapping("/api/ai")
public class AiCareerController {

    @Autowired
    private AiCareerService aiCareerService;

    @PostMapping("/chat")
    public Map<String, String> chat(
            @RequestBody AiChatRequest request) {

        String answer =
                aiCareerService.chatWithCareerAi(
                        request.getEmail(),
                        request.getQuestion()
                );

        return Map.of(
                "answer",
                answer
        );
    }

    @PostMapping("/rewrite")
    public Map<String, String> rewrite(
            @RequestBody AiRewriteRequest request) {

        String rewritten =
                aiCareerService.rewriteResumeText(
                        request.getText(),
                        request.getTargetRole()
                );

        return Map.of(
                "rewritten",
                rewritten
        );
    }
}