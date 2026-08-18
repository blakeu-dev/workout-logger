FROM eclipse-temurin:21-jdk

WORKDIR /workout-logger

COPY target/workout-logger-*.jar workout-logger.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "workout-logger.jar"]