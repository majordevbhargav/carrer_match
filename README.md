Career Match

AI-powered platform that analyzes resumes and matches job seekers to suitable roles based on their skills and experience — automating a process that's traditionally manual and time-consuming.

Overview

Career Match takes a candidate's resume and job/skill data, then uses AI-driven analysis to surface the roles that best fit their profile. The goal is to cut down the manual effort recruiters and job seekers spend matching resumes to job descriptions.

Features
Resume parsing and skill/experience extraction
AI-powered matching between candidate profiles and job roles
REST API layer connecting frontend and backend
PostgreSQL-backed persistent storage for candidate and job data
Tech Stack

Frontend: Next.js Backend: Spring Boot (Java) Database: PostgreSQL Integration: Axios (REST API calls between frontend and backend)

Architecture
Next.js (Frontend)
      │
      │  Axios / REST API
      ▼
Spring Boot (Backend)
      │
      ▼
PostgreSQL (Database)
Getting Started
Prerequisites
Node.js (v18+ recommended)
Java 17+
PostgreSQL (local instance or hosted)
Maven
Backend Setup
bash
cd backend
# Configure your database connection in application.properties
mvn clean install
mvn spring-boot:run
Frontend Setup
bash
cd frontend
npm install
npm run dev
Environment Variables

Create a .env file in the frontend directory:

NEXT_PUBLIC_API_BASE_URL=http://localhost:8080

Configure application.properties (or .env for backend, if used) with:

spring.datasource.url=jdbc:postgresql://localhost:5432/career_match
spring.datasource.username=your_username
spring.datasource.password=your_password
API Endpoints
Method	Endpoint	Description
POST	/api/resume/upload	Upload and parse a resume
GET	/api/matches/{userId}	Get matched roles for a user
GET	/api/jobs	List available job postings

(Update this table with your actual endpoints.)

Project Structure
career-match/
├── frontend/          # Next.js app
├── backend/           # Spring Boot app
│   ├── src/main/java
│   └── src/main/resources
└── README.md
Roadmap
 Improve AI matching accuracy with better resume parsing
 Add authentication for job seekers and recruiters
 Deploy to production (AWS/Vercel)
Author

Dev Bhargav GitHub · LinkedIn

License

This project is open source and available under the MIT License.
