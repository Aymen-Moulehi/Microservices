FROM eclipse-temurin:18-jdk-alpine
EXPOSE 8761
ADD target/eureka*.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]