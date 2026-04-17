# Importing JDK and copying required files
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy Maven wrapper and POM
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw

# Download dependencies to speed up future builds
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Compile skipping tests
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dserver.port=${PORT:-8080}","-jar","app.jar"]