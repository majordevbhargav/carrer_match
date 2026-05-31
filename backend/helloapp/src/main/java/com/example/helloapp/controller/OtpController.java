package com.example.helloapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.dto.OtpRequest;
import com.example.helloapp.dto.OtpVerifyRequest;
import com.example.helloapp.service.OtpService;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public Map<String, String> sendOtp(
            @RequestBody OtpRequest request) {

        String message =
                otpService.sendOtp(
                        request.getEmail()
                );

        return Map.of(
                "message",
                message
        );
    }

    @PostMapping("/verify")
    public Map<String, Object> verifyOtp(
            @RequestBody OtpVerifyRequest request) {

        boolean verified =
                otpService.verifyOtp(
                        request.getEmail(),
                        request.getOtp()
                );

        return Map.of(
                "verified",
                verified,
                "message",
                verified
                        ? "OTP verified successfully"
                        : "Invalid or expired OTP"
        );
    }
}