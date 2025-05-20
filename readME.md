# IAMPractise Application Suite

This project is a microservices-based application suite that includes multiple services such as `Albums-Resource-Server`, `Photos-Resource-Server`, `APIGateway`, `DiscoveryService`, `ResourceServer`, and `PhotoAppWebClient`. Each service has its own role and dependencies.

## Prerequisites

Before starting, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.8.1** or higher
- **Docker** (optional, for running dependencies like Keycloak)
- **Postman** or any API testing tool (optional, for testing APIs)

## Services Overview

1. **DiscoveryService**: Eureka server for service discovery.
2. **APIGateway**: API Gateway using Spring Cloud Gateway.
3. **Albums-Resource-Server**: Resource server for managing albums.
4. **Photos-Resource-Server**: Resource server for managing photos.
5. **ResourceServer**: OAuth2 resource server for user-related operations.
6. **PhotoAppWebClient**: A web client for interacting with the backend services.

## Dependencies

- **Keycloak**: Used for OAuth2 authentication and authorization.
- **Eureka Server**: For service discovery.
- **Spring Cloud Gateway**: For routing requests to microservices.

## Setup Instructions

### 1. Clone the Repository

git clone https://github.com/your-repo/IAMPractise.git
cd IAMPractise

### 2. Start Keycloak
start it on localhost:8080 using: .\kc.bat start-dev

### 3. Start Resource Server
start it on localhost:8081 using: mvn spring-boot:run


### 4. Start Discovery Service

### 3. Start Discovery Service

### 3. Start Discovery Service