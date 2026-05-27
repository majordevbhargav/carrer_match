package com.example.helloapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloapp.entity.ResumeAnalysis;

public interface ResumeAnalysisRepository
extends JpaRepository<
ResumeAnalysis,
Long>{

List<ResumeAnalysis>
findByEmail(
String email
);

}