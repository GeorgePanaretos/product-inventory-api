# Use a base image with OpenJDK 11
FROM openjdk:11-jdk-slim

VOLUME /tmp

# Copy the jar file built by Maven
COPY target/inventory-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]