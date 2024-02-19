FROM gradle:7.2.0-jdk17 AS builder

WORKDIR /app

COPY . .
RUN ./gradlew assemble check

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

CMD java -jar app.jar