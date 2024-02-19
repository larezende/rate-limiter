FROM gradle:7.2.0-jdk17 AS builder

WORKDIR /app
COPY . .
RUN ./gradlew assemble check

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app /app

EXPOSE 8080

ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
CMD java -jar app.jar