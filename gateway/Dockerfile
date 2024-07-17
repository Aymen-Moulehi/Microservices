FROM eclipse-temurin:18-jdk-alpine
EXPOSE 8000
ADD target/gateway*.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]