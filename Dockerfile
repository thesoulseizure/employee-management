# Use a Maven base image to build the application
FROM maven:3.9.6-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image for the runtime
FROM openjdk:17-jdk-slim

# Install the Tomcat Native library
RUN apt-get update && apt-get install -y libtcnative-1

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/employee-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Railway will override this with the PORT env variable)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
