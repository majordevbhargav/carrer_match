package com.example.helloapp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloapp.entity.User;
import com.example.helloapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public User saveUser(User  user){
        if(user.getRole()==null){
            user.setRole("USER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
    public User login(String email,String password){

    User user = userRepository
                    .findByEmail(email)
                    .orElse(null);

    if(user!=null &&
       passwordEncoder.matches(
       password,
       user.getPassword()
       )){

        return user;
    }

    return null;
}
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    
}
