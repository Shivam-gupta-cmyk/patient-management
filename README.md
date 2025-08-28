# Auth Service README

A complete guide to set up, run, and use the Auth Service of the Patient Management system. This service is built with Java 21 and Spring Boot and exposes an authentication endpoint that issues a token upon successful login.

## Overview

- Module: auth-service
- Purpose: Authenticate users and issue tokens for subsequent authorized requests
- Default port: 4005 (see `auth-service/src/main/resources/application.properties`)

## Features

- RESTful API with Spring MVC
- Data persistence via Spring Data JPA
- Jakarta EE imports for modern Java APIs
- Lombok to reduce boilerplate
- Token-based authentication (token string issued on login)
- Ready for local development with Maven or Gradle

## Tech Stack

- Java 21
- Spring Boot (web, mvc)
- Spring Data JPA
- Jakarta EE imports
- Lombok
- Relational database (H2/PostgreSQL/MySQL). A seed user is provided via `data.sql`.

## Getting Started

### Prerequisites

- Java 21 (JDK 21)
- Build tool (choose one):
  - Maven 3.9+ (or the Maven wrapper `mvnw`)
  - Gradle 8+ (or the Gradle wrapper `gradlew`)
- Database: You can start with the default configuration (H2 or configured DB). A sample user is seeded by `data.sql`.

### Clone

Use your preferred Git client. From the monorepo root:

- Clone the repository and open a terminal in the project root: `patient-management`

### Configure

- Application name and port are configured in:
  - `auth-service/src/main/resources/application.properties`
  - Default: `server.port=4005`
- Seed data is provided in:
  - `auth-service/src/main/resources/data.sql`
    - Inserts a user with email `testuser@test.com` and password `password123` (bcrypt-hashed in the file) and role `ADMIN`.
- If you need to point to an external database, add the relevant Spring datasource properties (URL, username, password, driver) to `application.properties`.

### Build

From the repository root:
- Using Maven:
  - Windows: `mvnw.cmd -q -pl auth-service -am clean package`
  - Cross-platform (if Maven wrapper is executable): `./mvnw -q -pl auth-service -am clean package`
- Using Gradle:
  - Windows: `gradlew.bat :auth-service:build`
  - Cross-platform: `./gradlew :auth-service:build`

### Run

From the repository root:
- Maven: `mvnw.cmd -q -pl auth-service -am spring-boot:run`
- Gradle: `gradlew.bat :auth-service:bootRun`

The service will start on http://localhost:4005 by default.

## API

Base path: `/auth`

- POST `/auth/login`
  - Description: Authenticate user and return a token.
  - Request body (JSON):
    - `email`: string
    - `password`: string
  - Responses:
    - 200 OK: `{ "token": "<token-string>" }`
    - 401 Unauthorized: no body

### Example Request

You can use the provided HTTP file to test login:
- File: `api-requests/auth-service/login.http`
- Example content:
```
POST http://localhost:4005/auth/login
Content-Type: application/json

{
  "email": "testuser@test.com",
  "password": "password123"
}
```

## Data and Users

- A default user is seeded via `data.sql`:
  - id: `223e4567-e89b-12d3-a456-426614174006`
  - email: `testuser@test.com`
  - password: `password123` (bcrypt hash stored)
  - role: `ADMIN`

## Integration via API Gateway

- The API Gateway runs on port 4004 (see `api-gateway/src/main/resources/application.yml`).
- Current routes shown in that file focus on `patient-service`. If you intend to expose `auth-service` through the gateway, add a similar route mapping to forward `/api/auth/**` to `auth-service:4005` and strip the prefix.

## Environment Variables (optional)

If you externalize configuration, common Spring Boot properties include:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SERVER_PORT` (overrides `application.properties` port)

## Running Tests

If/when tests exist in this module:
- Maven: `mvnw.cmd -q -pl auth-service -am test`
- Gradle: `gradlew.bat :auth-service:test`

## Troubleshooting

- Port already in use: Change `server.port` in `auth-service/src/main/resources/application.properties` or free the port.
- Cannot login / 401:
  - Ensure the seed data has been applied. On first start with an empty DB, Spring will run `data.sql`.
  - Verify you are posting to the correct port and path: `http://localhost:4005/auth/login`.
  - Check logs for authentication errors.
- Database connectivity issues:
  - Provide correct datasource URL/credentials in `application.properties` or environment variables.

## License

If this repository has a license, include its details here. Otherwise, define your licensing terms.
