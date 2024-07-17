FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8081
ADD target/planet*.jar planet.jar
ENTRYPOINT ["java", "-jar", "planet.jar"]