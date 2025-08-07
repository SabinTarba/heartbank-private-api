FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} heartbank-private-api.jar
ENTRYPOINT ["java", "-jar", "/heartbank-private-api.jar"]
