# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean install -U

# Stage 2: Run the application
# Use a lightweight JRE base image for the final container
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the JAR file from the build stage
# Adjust 'your-application-0.0.1-SNAPSHOT.jar' to your actual artifact name and version
COPY --from=build /app/target/*.jar /application/app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/application/app.jar"]
