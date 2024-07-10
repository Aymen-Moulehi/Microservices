

# Microservices with Spring Boot Project
![Static Badge](https://img.shields.io/badge/License-MIT-blue)
![Static Badge](https://img.shields.io/badge/Java-17-green)
![Static Badge](https://img.shields.io/badge/Maven-3.9.8-green)
![Static Badge](https://img.shields.io/badge/Spring_Boot-3.3.1-green)
## Project Overview

This project is a comprehensive demonstration of building a microservices architecture using Spring Boot. It showcases the design, development, and deployment of microservices-based applications, focusing on scalability, maintainability, and efficiency. Through this project, various aspects of microservices development are covered, including inter-service communication, service discovery, centralized configuration, and containerization with Docker and Docker Compose.

## Technologies and Tools Used

- Programming Language: Java 17
- Framework: Spring Boot 3.3.1
- Build Tool: Maven 3.9.8
- Containerization: Docker, Docker Compose
- Service Discovery: Eureka
- Configuration Management: Spring Cloud Config
- Fault Tolerance: Resilience4j
- Monitoring and Logging: Spring Boot Actuator

## Prerequisites
- Java 17
- Maven 3.9.8
- Docker
- Docker Compose

## Running the Project

### Clone the Repository
```bash
git clone https://github.com/Aymen-Moulehi/Microservices.git
cd microservices-springboot
```

### Build and Run the Docker Containers
From the root directory of the project, run:
```bash
docker-compose up --build
```

### Build the Project
Navigate to each microservice directory and build the project using Maven:
```bash
cd eureka
mvn clean install
cd ../galaxy
mvn clean install
cd ../planet
mvn clean install
cd ../gateway
mvn clean install
```
## License
This project is licensed under the [MIT License](LICENSE).

## Conclusion
This project provided valuable hands-on experience in designing and implementing a microservices architecture using Spring Boot. It covered essential microservices concepts and tools, preparing for real-world applications where scalability, resilience, and maintainability are crucial.
