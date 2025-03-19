# spring_virtual_threads

## Description

This repository contains a first approach to virtual threads on Spring Boot using Java 21 and Spring 3.4.3.
It contains a server and client to test the performance with/without virtual threads and using webflux .

Tomcat server is limited to only 10 threads.

## Features
- **Spring Boot**: Robust backend framework.
- **REST API server**: Provides a response maintaining a delay of 3 seconds.
- **REST API client**: Provides endpoints to test requests responses.

## Requirements
- Java 21+
- Maven 3+
- Docker (optional, for deployment)

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/IA-Cross/spring_ia_test.git
   cd spring_ia_test

2. Build the project
   ```sh
   mvn clean install
3. Run the application:
   ```sh
   mvn spring-boot:run

## How to use it
1. Run the server project first and then client
2. Test the basic request with http://localhost:8080/client
3. You can use an Apache Binaries tool named ab to send multiple request with the following command:
    ```sh
     ./ab -c 20 -n 60 http://localhost:8080/client
4. You can test the WebFlux implementation with the following command:
    ```sh
     ./ab -c 20 -n 60 http://localhost:8080/client-flux


## Results
- No virtual threads: 21.129 seconds

![Result without virtual threads](/image/VirtualThreadsFalse.png)

- Virtual threads: 12.331 seconds

![Results with virtual threads](/image/VirtualThreadsTrue.png)

- Virtual threads + WebFlux 0.368 seconds

![Results with virtual threads + flux](/image/VirtualThreadsFlux.png)

**_NOTE:_**  WebFlux does not wait for a response from the server.


