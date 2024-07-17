FROM eclipse-temurin:18-jdk-alpine
EXPOSE 8888
ADD target/config*.jar config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]