Use the official Gradle image with JDK 21 as the build environment.
FROM gradle:8.8-jdk21 AS build
WORKDIR /app

Copy the project’s Gradle wrapper and build files.
COPY gradlew gradlew.bat ./
COPY gradle gradle

Copy the rest of the project files.
COPY . .

Build a fat JAR (you should use Shadow if available; if not, adjust accordingly).
If your project is set up for shadowJar, use this:
RUN ./gradlew shadowJar --no-daemon

Use a smaller runtime image.
FROM eclipse-temurin:21-jre
WORKDIR /app

Copy the fat JAR from the build stage.
Adjust the JAR name if needed; for example, if your jar name is "SimpleGpsTrackerServer-all.jar"
COPY --from=build /app/build/libs/*-all.jar app.jar

Expose the port that Cloud Run will use (default 8080)
EXPOSE 8080 ENV PORT=8080 ENTRYPOINT ["java", "-jar", "app.jar"]