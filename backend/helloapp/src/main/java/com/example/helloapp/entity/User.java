package com.example.helloapp.entity;


import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    private String role="USER";

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName=fullName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}

/*
Meaning:
"This Java class should become a database table"
Spring + JPA sees:

@Entity

and says:

"Got it. I will map this class into PostgreSQL."
@Table(name="users")

Meaning:

Create a database table named:
users

Without it, Spring would usually create:

User

but we explicitly told it:

users
@Id

Meaning:

This field is the primary key

Database:

users

id

Every user gets a unique identity.

@GeneratedValue(strategy = GenerationType.IDENTITY)

Meaning:

Generate IDs automatically

So:

User 1:

id=1

User 2:

id=2

User 3:

id=3

You don't manually assign them.

private String fullName;
private String email;
private String password;

These become columns:

users table

+----+-----------+----------------+-----------+
| id | fullName  | email          | password  |
+----+-----------+----------------+-----------+
| 1  | Dev       | dev@gmail.com  | ******    |
| 2  | Alex      | alex@gmail.com | ******    |
+----+-----------+----------------+-----------+

For your Career Match app later, User.java will grow into something like:

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private String college;

    private String skills;

    private String resumeUrl;

    private String linkedinUrl;

    private String role;

}

Then AI can use those fields:

Resume
      ↓
Extract Skills
      ↓
Store in User
      ↓
Compare with Job
      ↓
Career Match Score

So User.java is basically:

Real person
       ↓
Java object
       ↓
Database row
*/