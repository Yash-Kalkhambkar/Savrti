# SAVRTI Plant Exchange API

A REST API for exchanging plants between users. Built with Spring Boot and deployed on Render.

## Live API

**Base URL:** https://savrti-plant-exchange-api.onrender.com

**Swagger UI:** https://savrti-plant-exchange-api.onrender.com/swagger-ui/index.html

> Note: The server may take 30-60 seconds to respond on the first request since it runs on a free tier.

## Tech Stack

- Java 17
- Spring Boot 3.3
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL (Supabase)
- Docker
- Deployed on Render

## Features

- User registration and login with JWT authentication
- List plants for exchange
- Request plants from other users
- Approve or reject requests
- Only plant owners can edit or delete their listings
- Passwords are encrypted with BCrypt

## How to Run Locally

1. Clone the repo
2. Make sure Java 17 is installed
3. Run with H2 in-memory database (default dev profile):

```bash
./mvnw spring-boot:run
```

4. Open http://localhost:8080/swagger-ui/index.html

## API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /api/auth/register | Register a new user | No |
| POST | /api/auth/login | Login and get JWT token | No |
| GET | /api/plants | Get all plants | Yes |
| POST | /api/plants | Add a new plant | Yes |
| PUT | /api/plants/{id} | Update your plant | Yes |
| DELETE | /api/plants/{id} | Delete your plant | Yes |
| POST | /api/requests | Request a plant | Yes |
| PATCH | /api/requests/{id}/status | Approve or reject a request | Yes |
| DELETE | /api/requests/{id} | Cancel your request | Yes |
| GET | /api/health | Health check | No |

## How to Test

1. Open Swagger UI
2. Use `POST /api/auth/register` to create an account
3. Use `POST /api/auth/login` to get your token
4. Click **Authorize** in Swagger and paste the token
5. Now you can test all endpoints
