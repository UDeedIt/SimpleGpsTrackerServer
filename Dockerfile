# Build stage: use Gradle with JDK 21
FROM gradle:8.8-jdk21 AS build
WORKDIR /app

# Copy all project files into the image
COPY . .

# Build a fat JAR with Shadow (make sure the project has shadowJar configured)
RUN ./gradlew shadowJar --no-daemon

# Runtime stage: smaller JRE image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the fat JAR from the build stage
COPY --from=build /app/build/libs/*-all.jar app.jar

# Cloud Run / container port
EXPOSE 8080
ENV PORT=8080

ENTRYPOINT ["java", "-jar", "app.jar"]
