FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8082
ADD target/galaxy*.jar galaxy.jar
ENTRYPOINT ["java", "-jar", "galaxy.jar"]