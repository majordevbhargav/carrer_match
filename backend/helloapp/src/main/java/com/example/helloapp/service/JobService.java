package com.example.helloapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloapp.entity.Job;
import com.example.helloapp.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public Map<String,Integer> matchJobs(
            List<String> userSkills){

        List<Job> jobs =
                jobRepository.findAll();

        Map<String,Integer> result =
                new HashMap<>();

        for(Job job : jobs){

            String[] requiredSkills =
                    job.getSkills().split(",");

            int matched=0;

            for(String skill : requiredSkills){

                if(userSkills.contains(
                        skill.trim()
                )){

                    matched++;
                }
            }

            int percentage=
                    (matched*100)
                    /
                    requiredSkills.length;

            result.put(
                    job.getTitle(),
                    percentage
            );

        }

        return result;
    }
}