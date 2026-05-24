package com.example.helloapp.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
@CrossOrigin (origins="http://localhost:3000")
public class HelloController {

    @GetMapping("/test")
    public String hello() {
        return "Career Match Backend Running";
    }
}
