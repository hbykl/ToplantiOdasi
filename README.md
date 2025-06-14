# Meeting Room Management Application

This repository contains a simple Spring Boot application that demonstrates basic user and meeting room management. It features a minimal web interface built with Thymeleaf and stores data in a MySQL database.

## Features

- User registration and login
- Creation, listing, update, and deletion of meeting rooms
- Cookie-based session handling
- Basic form validation with Spring Validation

## Requirements

- Java 17
- Maven
- MySQL server (or change configuration to use a different database)

## Setup

1. Clone the repository and navigate into the project directory.
2. Configure database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/myDatabase
spring.datasource.username=<your user>
spring.datasource.password=<your password>
```

3. Build the project with Maven:

```bash
./mvnw clean package
```

## Running the Application

Use the Spring Boot Maven plugin to run the application:

```bash
./mvnw spring-boot:run
```

The application starts on port 8080. Access `http://localhost:8080/hutu/login` for the login page or `http://localhost:8080/hutu/register` to create a new account.

## Directory Structure

- `src/main/java/com/example/toplantiOdasi` – main application package
  - `Classes/` – JPA entity classes `User` and `Room`
  - `Repositorys/` – Spring Data JPA repositories
  - `ServiceImpl/` and `Services/` – service layer for business logic
  - `MyController.java` – handles web requests and session management
- `src/main/resources/templates/` – Thymeleaf HTML templates
- `src/test/java/` – contains a minimal context loading test

## Example Endpoints

| HTTP method | Path                         | Description                 |
|-------------|-----------------------------|-----------------------------|
| GET/POST    | `/hutu/login`               | Show login page / process login |
| GET         | `/hutu/register`            | Display user registration form |
| POST        | `/hutu/saveUser`            | Register a new user |
| GET         | `/hutu/user/rooms`          | List all meeting rooms |
| GET         | `/hutu/user/newRoom`        | Form to create a new room |
| POST        | `/hutu/user/saveRoom`       | Save a new room |
| GET         | `/hutu/user/update/{id}`    | Edit an existing room |
| POST        | `/hutu/user/updateRoom`     | Update room information |
| GET         | `/hutu/user/delete/{id}`    | Delete a room |
| GET/POST    | `/hutu/user/logout`         | Logout and clear session |

## Notes

- The application uses cookies (`User` and `info`) to store the current user ID and feedback messages.
- `spring.jpa.show-sql=true` is enabled for debugging and prints SQL statements to the console.

## Testing

Run tests using Maven:

```bash
./mvnw test
```

The project currently contains only a single context loading test.
