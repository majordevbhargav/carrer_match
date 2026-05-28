package com.example.helloapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.helloapp.entity.Application;

@Repository
public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    List<Application> findByEmail(String email);
}