package com.example.helloapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills=skills;
    }
}