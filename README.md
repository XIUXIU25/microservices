# 🧩 UMI: Universal Microservice Infrastructure

UMI is a complete microservices architecture built with Spring Boot, Spring Cloud, Docker, and Keycloak. It provides a scalable, secure, and maintainable foundation for building cloud-native applications using modern Java technologies.

---

## 📦 Project Structure

```
umi/
├── api-gateway/              # Gateway routing and authentication
├── config-server/            # Centralized externalized configuration
├── discovery-server/         # Service registry using Eureka
├── product-service/          # Product catalog service
├── order-service/            # Order processing service
├── inventory-service/        # Inventory validation and availability check
├── docker-compose.yml        # Container orchestration
└── README.md                 # This file
```

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.x**
- **Spring Cloud 2025.x**
- **Spring Cloud Gateway**
- **Eureka Discovery**
- **Keycloak (OAuth2, JWT)**
- **Spring Cloud Config**
- **Feign Client / WebClient**
- **Zipkin + Sleuth for tracing**
- **Docker & Docker Compose**

---

## 🔐 Security

All services are protected using **Keycloak** and OAuth2. Each request to the backend services requires a valid JWT token. Authentication is managed at the API Gateway.

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt())
        .build();
}
```

---

## 🚀 Running the Project

### ✅ Prerequisites

- JDK 21
- Maven 3.8+
- Docker & Docker Compose
- (Optional) IntelliJ IDEA

### 🔧 Running with Maven (for development)

```bash
# Start Eureka and Config Server first
cd discovery-server && mvn spring-boot:run
cd config-server && mvn spring-boot:run

# Then start API Gateway and services
cd api-gateway && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
```

### 🐳 Running with Docker Compose (recommended)

```bash
docker-compose up --build
```

---

## 🌐 Service Endpoints

| Service            | URL                         |
|--------------------|-----------------------------|
| API Gateway        | `http://localhost:8085`     |
| Eureka Dashboard   | `http://localhost:8761`     |
| Keycloak Admin     | `http://localhost:8080`     |
| Zipkin             | `http://localhost:9411`     |

---

## 🧪 Testing

Unit and integration tests are available in each service:

```bash
mvn clean test
```

You can also test APIs manually using Postman, Insomnia, or curl.

---

## ✨ Features

- ✅ Secure microservices with Keycloak + JWT
- ✅ Centralized config using Spring Cloud Config
- ✅ Service discovery via Eureka
- ✅ Routing and load balancing via API Gateway
- ✅ Fault tolerance and inter-service communication with Feign/WebClient
- ✅ Distributed tracing with Zipkin and Sleuth
- ✅ Containerized with Docker and orchestrated via Docker Compose

---

## 📄 License

This project is open for personal and educational use. For commercial use or modifications, please contact the maintainer.

---

## 🙌 Maintainer

**Xiuxiu Ying**  
GitHub: [@XIUXIU25](https://github.com/XIUXIU25)