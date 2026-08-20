# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:21-jdk

WORKDIR /workout-logger

COPY --from=build /build/target/workout-logger-*.jar workout-logger.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "workout-logger.jar"]