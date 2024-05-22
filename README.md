# Learning Navigator - Exam Enrollment API Service

## Overview

The Exam Enrollment API Service is a RESTful API developed using Spring Boot for managing the exam enrollment process in a Learning Management System (LMS). This service handles CRUD operations for Students, Subjects, and Exams, and uses MySQL for data persistence. The service also includes an Easter Egg feature that provides random facts about numbers using the Numbers API.

## Features

- CRUD operations for Students, Subjects, and Exams
- Foreign Key relationships between entities
- Student registration for exams only after enrolling in the corresponding subject
- Graceful error handling with appropriate HTTP codes
- Global Exception Handling using `@ControllerAdvice`
- Basic unit tests using MockMvc and Mockito
- Easter Egg feature providing random facts about numbers

## Endpoints

- **Student Endpoints**
  - `GET /students` - Retrieve all students.
  - `GET /students/{id}` - Retrieve a student by ID.
  - `POST /students` - Register a new student.
  - `PUT /students/{id}` - Update an existing student.
  - `DELETE /students/{id}` - Delete a student by ID.
  
- **Subject Endpoints**
  - `GET /subjects` - Retrieve all subjects.
  - `GET /subjects/{id}` - Retrieve a subject by ID.
  - `POST /subjects` - Create a new subject.
  - `PUT /subjects/{id}` - Update an existing subject.
  - `DELETE /subjects/{id}` - Delete a subject by ID.
  
- **Exam Endpoints**
  - `GET /exams` - Retrieve all exams.
  - `GET /exams/{id}` - Retrieve an exam by ID.
  - `POST /exams` - Create a new exam.
  - `POST /exams/{examId}/register/{studentId}` - Register a student for a specific exam.
  - `PUT /exams/{id}` - Update an existing exam.
  - `DELETE /exams/{id}` - Delete an exam by ID.
  
- **Easter Egg Feature**
  <!-- - `GET /hidden-feature/{number}` - Generate a fact about the number passed as the path parameter. -->

## Implemented Code

### Controllers

- `StudentController`, `SubjectController`, `ExamController`, and `EasterEggController` contain endpoints for managing students, subjects, exams, and the Easter egg feature, respectively.

### DTOs

- `StudentDto`, `SubjectDto`, and `ExamDto` are Data Transfer Objects representing student, subject, and exam entities.

### Entities

- `Student`, `Subject`, and `Exam` are JPA entities representing student, subject, and exam entities, respectively.

### Exception Handling

- `GlobalExceptionHandler` handles custom exceptions such as `NotFoundException` and `AlreadyExistsException`.

### Repositories

- `StudentRepository`, `SubjectRepository`, and `ExamRepository` provide CRUD operations for student, subject, and exam entities, respectively.

### Services

- `StudentService`, `SubjectService`, and `ExamService` contain business logic for managing students, subjects, and exams, respectively.

### Configuration

- `RestClientConfig` provides configuration for the RestTemplate.

### Unit Tests

- Include basic unit tests using MockMvc and Mockito.

### Main Application

- `LearningNavigatorApplication` is the main Spring Boot application class.

## Tools and Technologies

- Java
- Spring Boot
- MySQL
- Gradle

## Setup and Usage

1. Clone the repository.
2. Configure MySQL database settings in `application.properties`.
3. Run the application using `./gradlew bootrun`.
4. Use endpoints to perform CRUD operations on students, subjects, and exams.
5. Access the Easter egg feature endpoint to generate random facts about numbers.
