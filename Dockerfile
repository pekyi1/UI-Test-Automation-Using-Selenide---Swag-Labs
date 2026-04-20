# Use a official Maven image as a parent image
FROM maven:3.9.6-eclipse-temurin-17-focal

# Set the working directory in the container
WORKDIR /app

# Install Chrome and dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' \
    && apt-get update && apt-get install -y \
    google-chrome-stable \
    --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

# Copy the pom.xml and any other files needed for dependency resolution
COPY pom.xml .

# Pre-fetch project dependencies to speed up the build
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src



# Command to run tests when the container starts
CMD ["mvn", "test", "-Dheadless=true"]
