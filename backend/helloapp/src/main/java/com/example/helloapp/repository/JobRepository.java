package com.example.helloapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.helloapp.entity.Job;

public interface JobRepository
extends JpaRepository<Job,Long>{

}