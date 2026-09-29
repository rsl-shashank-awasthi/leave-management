# Leave Management API

## Project Overview
This project is a RESTful Leave Management API built with Spring Boot. It allows employees to submit and manage leave requests through a clean layered architecture, using an in-memory `ConcurrentHashMap` database for storage.

## Build and Run Instructions
1. Ensure Java 17 and Maven are installed.
2. Clone the repository and navigate to the root directory.
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. The application will start on `http://localhost:8080` (active profile: dev).

## API Endpoint Summary
| Method | Endpoint | Description |
|---|---|---|
| GET | `/leaves` | Retrieve all leave requests |
| GET | `/leaves/{id}` | Retrieve a leave request by ID |
| POST | `/leaves` | Create a new leave request |
| PUT | `/leaves/{id}` | Update an existing leave request |
| DELETE | `/leaves/{id}` | Delete a leave request |


