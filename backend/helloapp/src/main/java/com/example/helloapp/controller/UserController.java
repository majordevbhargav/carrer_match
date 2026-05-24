package com.example.helloapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.helloapp.entity.User;
import com.example.helloapp.service.UserService;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
/*
You're now looking at your UserController, which is the entry gate of your backend. Every line has a purpose. Let's dissect it piece by piece 🔬
Frontend
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
package c.example.helloapp.controller;

Meaning:

This file belongs inside:

src/main/java/c/example/helloapp/controller

Java uses package names like an address.

import java.util.List;

Meaning:

Import Java's List collection

Needed because:

public List<User> getUsers()

returns multiple users.

Example:

[User1, User2, User3]
import org.springframework.beans.factory.annotation.Autowired;

Meaning:

Spring automatically creates and injects objects

Instead of:

UserService userService = new UserService();

Spring creates it for you.

import org.springframework.web.bind.annotation.*;

Meaning:

Import Spring web tools:

@RestController
@GetMapping
@PostMapping
@RequestBody
@RequestMapping
import c.example.helloapp.entity.User;

Meaning:

Bring your User.java class here.

Without it:

User user

would cause:

Cannot resolve symbol User
import c.example.helloapp.service.UserService;

Meaning:

Bring the service layer here.

Flow:

Controller
    ↓
Service
@RestController

Meaning:

This class handles API requests

Without it, Spring would not know this class serves endpoints.

@RequestMapping("/api/users")

Meaning:

Every API in this controller starts with:

/api/users

Example:

@GetMapping

becomes:

/api/users

and:

@PostMapping("/register")

becomes:

/api/users/register
@CrossOrigin(origins = "http://localhost:3000")

Meaning:

Allow frontend:

localhost:3000

to talk with backend:

localhost:8080

Without it:

CORS ERROR ❌
@Autowired
UserService userService;

Meaning:

Spring automatically creates:

UserService

and puts it here.

Think:

Controller: "I need UserService"

Spring: "Here you go."
@PostMapping("/register")

Meaning:

Handle:

POST /api/users/register

Example request:

{
"fullName":"Dev",
"email":"dev@gmail.com",
"password":"12345"
}
public User register(@RequestBody User user)

Meaning:

Take incoming JSON and convert it into:

User object

JSON:

{
"fullName":"Dev"
}

becomes:

user.fullName="Dev";
return userService.saveUser(user);

Meaning:

Send data to service:

Controller
    ↓
Service
    ↓
Repository
    ↓
Database
@GetMapping

Meaning:

Handle:

GET /api/users
public List<User> getUsers()

Meaning:

Return all users from database.

Example response:

[
{
"id":1,
"fullName":"Dev",
"email":"dev@gmail.com"
},
{
"id":2,
"fullName":"Alex",
"email":"alex@gmail.com"
}
]

Complete flow:

Next.js frontend
       ↓
POST /api/users/register
       ↓
UserController
       ↓
UserService
       ↓
UserRepository
       ↓
PostgreSQL

*/
