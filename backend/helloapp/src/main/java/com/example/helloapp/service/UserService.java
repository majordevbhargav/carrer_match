package com.example.helloapp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloapp.entity.User;
import com.example.helloapp.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User saveUser(User  user){
       return userRepository.save(user);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    
}
