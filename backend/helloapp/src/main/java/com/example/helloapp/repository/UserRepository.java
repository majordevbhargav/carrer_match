package com.example.helloapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloapp.entity.User;
public interface UserRepository extends JpaRepository<User , Long>{
    
}
