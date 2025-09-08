# Etapa 1: Build
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app
COPY . .

RUN gradle bootJar --no-daemon

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

