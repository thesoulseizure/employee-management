# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Install the Tomcat Native library and dependencies
RUN apt-get update && apt-get install -y libtcnative-1

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file (built by Maven)
COPY target/employee-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Railway will override this with the PORT env variable)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
