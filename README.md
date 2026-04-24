# Book Store Management System

A powerful and community-ready book store management application built with **Spring Boot 3.3.1**, featuring REST APIs, JWT authentication, and Docker support.

---

## Features

- **CRUD Operations** for books
- **Personal Book List** management
- **REST API** with full Swagger/OpenAPI documentation
- **JWT Authentication** for secure access
- **Role-Based Access Control** (USER/ADMIN)
- **Unit Tests** with JUnit and Mockito
- **Docker & Docker Compose** for easy deployment
- **CI/CD Pipeline** with GitHub Actions

---

## Tech Stack

| Component | Technology |
|------------|------------|
| Backend | Spring Boot 3.3.1 |
| Frontend | Thymeleaf |
| Database | MySQL 8.0 |
| Security | Spring Security + JWT |
| Documentation | Swagger/OpenAPI (springdoc) |
| Testing | JUnit 5, Mockito |
| Containerization | Docker, Docker Compose |
| CI/CD | GitHub Actions |

---

## Quick Start

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose (optional)

### Run Locally

```bash
# Clone the repository
git clone https://github.com/MiguelAntonioRS/BookStore-with-Spring-Boot.git
cd BookStore-with-Spring-Boot

# Build and run
mvn spring-boot:run
```

The app will be available at: http://localhost:8080

### Run with Docker

```bash
docker-compose up -d
```

---

## API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### Authentication Endpoints

```
POST /api/v1/auth/register - Register a new user
POST /api/v1/auth/login    - Login and get JWT token
```

### Book Endpoints (Requires JWT)

```
GET    /api/v1/books           - Get all books
GET    /api/v1/books/{id}      - Get book by ID
POST   /api/v1/books           - Create new book
PUT    /api/v1/books/{id}      - Update book
DELETE /api/v1/books/{id}      - Delete book
GET    /api/v1/books/search/author?name=Robert - Search by author
GET    /api/v1/books/search/name?name=Clean    - Search by name
```

### Using the API

1. Register or login to get a JWT token
2. Include the token in requests:

```bash
curl -H "Authorization: Bearer YOUR_TOKEN" \
     http://localhost:8080/api/v1/books
```

---

## Project Structure

```
src/main/java/com/bookStore/bookStore/
├── config/           # Configuration classes
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
├── controller/       # REST Controllers
│   ├── AuthController.java
│   └── BookRestController.java
├── dto/              # Data Transfer Objects
│   ├── ApiResponse.java
│   ├── AuthResponse.java
│   ├── BookDTO.java
│   └── ...
├── entity/           # JPA Entities
│   ├── BookEntity.java
│   ├── Role.java
│   └── User.java
├── exception/        # Exception Handling
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/       # JPA Repositories
│   ├── BookRepository.java
│   └── UserRepository.java
├── security/         # Security Components
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtService.java
└── service/          # Business Logic
    └── BookService.java
```

---

## Testing

```bash
# Run all tests
mvn test

# Run with coverage report
mvn test jacoco:report
```

**Test Coverage**: 17 tests covering services, controllers, and security.

---

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | Database URL | jdbc:mysql://localhost:3306/book_db |
| `SPRING_DATASOURCE_USERNAME` | Database user | root |
| `SPRING_DATASOURCE_PASSWORD` | Database password | root |
| `jwt.secret` | JWT signing key | (configured in properties) |
| `jwt.expiration` | Token expiration (ms) | 86400000 (24h) |

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License.

---

## Author

**Miguel Antonio**

- GitHub: [MiguelAntonioRS](https://github.com/MiguelAntonioRS)