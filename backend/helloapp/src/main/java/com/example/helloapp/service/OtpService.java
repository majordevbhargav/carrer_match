package com.example.helloapp.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.entity.OtpVerification;
import com.example.helloapp.repository.OtpVerificationRepository;

@Service
public class OtpService {

    @Autowired
    private OtpVerificationRepository repository;

    @Autowired
    private EmailService emailService;

    public String sendOtp(String email) {

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000));

        OtpVerification verification = new OtpVerification();

        verification.setEmail(email);
        verification.setOtp(otp);
        verification.setExpiryTime(
                LocalDateTime.now().plusMinutes(10));
        verification.setVerified(false);

        repository.save(verification);

        System.out.println("Career Match OTP for " + email + " is: " + otp);

        try {
            emailService.sendOtpEmail(email, otp);
            return "OTP sent successfully";
        } catch (Exception error) {
            System.out.println("Email sending failed: " + error.getMessage());
            return "OTP generated. Email failed, check backend console.";
        }
    }

    public boolean isEmailVerified(String email) {

        return repository.findTopByEmailOrderByIdDesc(email)
                .map(OtpVerification::isVerified)
                .orElse(false);
    }

    public boolean verifyOtp(
            String email,
            String otp) {

        Optional<OtpVerification> optionalOtp = repository.findTopByEmailOrderByIdDesc(
                email);

        if (optionalOtp.isEmpty()) {
            return false;
        }

        OtpVerification verification = optionalOtp.get();

        if (verification.isVerified()) {
            return true;
        }

        if (verification.getExpiryTime().isBefore(
                LocalDateTime.now())) {
            return false;
        }

        if (!verification.getOtp().equals(otp)) {
            return false;
        }

        verification.setVerified(true);
        repository.save(verification);

        return true;
    }
}