# Transaction Service

Transaction Service is a Java application developed using Spring Boot (version 3.2.2) and Java 21. It serves as a backend service for handling fee transactions, viewing transaction details, and generating transaction receipts. The application utilizes an H2 database for data storage.

## Technologies

- Java 21
- Spring Boot 3.2.2
- H2 Database
- JUnit
- Swagger for API documentation
- Spring Boot Actuator for monitoring and managing application

## Features

1. **Get all transaction records:**
    - The service allows users to get all the transaction records against student id.

2. **Process payment:**
    - This service allows to process payment .

3. **Receipt Generation:**
    - PDF Generation: Receipts are made PDF format for easy storage and printing.

4. **Swagger Documentation:**
    - API documentation is available through Swagger for easy exploration and integration.
    - Accessible at [http://localhost:8083/transaction-service/swagger-ui/index.html#/](http://localhost:8083/transaction-service/swagger-ui/index.html#/).

### Spring Boot Actuator

- Monitor and manage the application in production with Spring Boot Actuator.
- Endpoints include health, metrics, info, and more.
- Accessible at [http://localhost:8083/transaction-service/actuator](http://localhost:8083/transaction-service/actuator).

## Unit Test Cases

- JUnit test cases are implemented to ensure the reliability and correctness of the application.

## Setup

1. Clone the repository.
2. Configure your IDE or build tool for a Spring Boot application.
3. Run the application and access the Swagger documentation to explore the APIs.

## Endpoints

- **Student:**
    - GET `/student/{studentId}` - get list of transactions for a specific student.
    - POST - /
    - GET `/view-receipt?transNumber=Txn-123` - View all transactions for a specific student roll number.

# Getting Started

To get started with the project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/HafizHamzaAhmad/transaction-service.git

2. Build Application:

   ```bash
   mvn clean install

3. Run Application:

   ```bash
   java -jar transaction-0.0.1-SNAPSHOT.jar

4. Access Swagger:

   ```bash
   http://localhost:8083/transaction-service/swagger-ui/index.html#/
