# Drone Task
[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![Coverage](https://img.shields.io/badge/coverage-67%25-yellow)

Drone Task is Java/Spring Boot Project

## Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure. Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

## Requirements

For building and running the application you need:

- [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.musalasoft.dronetask.DroneTaskApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## API Documentation

swagger-ui url
```
http://localhost:8080/swagger-ui/index.html
```

### Upload Image for Medication

This is a separate API to upload an image file for existing medication. The file will be uploaded to `Amazon S3`
and The stored file path will be saved to the `MySQL` database.

```bash
curl --location --request PUT 'localhost:8080/v1/dispatch/medication/image/upload/1' \
--form 'file=@"/C:/Users/stela/Pictures/336584.jpg"'
```
