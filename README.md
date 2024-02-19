# Rate Limiter Demo Project

This is a simple rate limiter demo project.

It is a simple Spring Boot application.

Uses mysql as main database.

And Redis as cache service for the rates.

It has 2 APIs:

1. User API /user
2. Quota API /quota

### User API

Contains basic CRUD operations for user.

### Quota API

Contains consume rate quota for user and list all users quotas usages.

## Running

To run everything locally you can use the docker-compose on the project root folder.

```docker-compose up --build ```

It should start the mysql and redis services and build and run the application.

Otherwise you can build the application with gradle and run the jar directly.

```./gradlew assemble ```

```java -jar build/libs/rate-limiter-0.0.1-SNAPSHOT.jar ```

## Configuration

The application needs the following environment variables:

- DATABASE_JDBC_URL: mysql jdbc url
- DATABASE_USERNAME: mysql username
- DATABASE_PASSWORD: mysql password
- REDIS_HOST: redis host
- REDIS_PORT: redis port
- REDIS_PASSWORD: redis password

## Testing

There are unit and integration tests for the application.

You can run them with gradle.

- For unit tests: 
```./gradlew test ```
- For integration tests: 
```./gradlew integrationTest ```