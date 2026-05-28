package com.example.helloapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.helloapp.entity.JobPost;

@Repository
public interface JobPostRepository
        extends JpaRepository<JobPost, Long> {

    List<JobPost> findByTitleContainingIgnoreCase(
            String title
    );

    List<JobPost> findByLocationContainingIgnoreCase(
            String location
    );
}