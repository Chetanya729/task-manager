# Task Manager REST API

A clean, layered Spring Boot REST API for managing tasks — built to demonstrate REST principles, layered architecture, and core OOP design patterns.

## Tech Stack

- **Language** : Java 21
- **Framework** : Spring Boot 3.x
- **Build Tool** : Maven
- **Utilities** : Lombok, Jakarta Bean Validation
- **Storage** : MongoDB Atlas (cloud-hosted)

## Features

- Full CRUD operations on tasks
- Filter tasks by status
- Bean validation on incoming requests with clear error messages
- Centralized exception handling returning consistent JSON error responses
- Thread-safe in-memory storage suitable for concurrent requests

## Project Structure

```
src/main/java/com/project1/task_manager/
├── TaskManagerApplication.java
├── model/
│   ├── Task.java
│   ├── Priority.java
│   └── Status.java
├── repository/
│   ├── TaskRepository.java          (interface)
│   └── InMemoryTaskRepository.java  (implementation)
├── service/
│   └── TaskService.java
├── controller/
│   └── TaskController.java
├── dto/
│   ├── TaskRequest.java
│   ├── TaskResponse.java
│   └── ErrorResponse.java
├── exception/
│   ├── TaskNotFoundException.java
│   └── GlobalExceptionHandler.java
└── config/
    └── WebConfig.java
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use the included Maven wrapper `./mvnw`)

### Running locally

The API will be available at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/tasks` | Create a new task |
| GET | `/tasks` | Get all tasks (optional `?status=` filter) |
| GET | `/tasks/{id}` | Get a task by ID |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |

### Sample requests

**Create a task**

'{
    "title": "Learn Spring Boot",
    "description": "Build a Task Manager API",
    "priority": "HIGH"
  }'

Response:

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Build a Task Manager API",
  "priority": "HIGH",
  "status": "TODO",
  "createdAt": "2026-05-27T10:30:00"
}
```

**Filter by status**

```bash
curl "http://localhost:8080/api/tasks?status=todo"
```

**Error response example (task not found)**

```json
{
  "status": 404,
  "message": "Task not found with id: 999",
  "path": "/api/tasks/999",
  "timestamp": "2026-05-27T10:30:00"
}
```

## Design Highlights

- **Layered architecture** — clear separation between HTTP handling (Controller), business logic (Service), and persistence (Repository).

- **Dependency inversion** — `TaskService` depends on the `TaskRepository` interface, not its concrete implementation. Storage can be swapped to JPA/MySQL with zero changes to service or controller code.

- **DTOs at the API boundary** — `TaskRequest` and `TaskResponse` decouple the public API contract from the internal domain model, preventing accidental data exposure.

- **Thread-safe storage** — `ConcurrentHashMap` and `AtomicLong` ensure correct behaviour under concurrent requests in a web server environment.

- **Centralized error handling** — a single `@RestControllerAdvice` handles all exceptions and returns consistent JSON error responses with HTTP status, message, path, and timestamp.

- **Bean validation** — `@Valid` plus `@NotBlank`, `@Size`, etc. provide declarative input validation with structured error messages.

- **Case-insensitive enum binding** — configured globally via Jackson (`application.properties`) for JSON bodies and a custom `ConverterFactory` for query parameters.

## Future Enhancements

- Replace in-memory storage with MongoDB + Spring Data JPA


Built as a learning project. Feel free to fork, study, and extend.
