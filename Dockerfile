# Use the official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/bodegastore-0.0.1.jar bodegastore-0.0.1

# Expose the port on which your Spring Boot application runs (default is 8080)
EXPOSE 8020

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "bodegastore-0.0.1.jar"]
