# Use a official Maven image as a parent image
FROM maven:3.9.6-eclipse-temurin-17-focal

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and any other files needed for dependency resolution
COPY pom.xml .

# Pre-fetch project dependencies to speed up the build
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Create a volume for Allure reports and screenshots
VOLUME /app/target/allure-results

# Command to run tests when the container starts
CMD ["mvn", "test", "-Dheadless=true"]
