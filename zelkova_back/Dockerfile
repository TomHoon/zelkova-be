# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file (adjust the path and name)
COPY build/libs/zelkova_back.jar zelkova_back.jar

# Set command to run the JAR
ENTRYPOINT ["java", "-jar", "zelkova_back.jar"]
