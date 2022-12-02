# Drone Task

Drone Task is Java/Spring Boot Project

## Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure. Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

## API Documentation

swagger-ui url
```
http://localhost:8080/swagger-ui/index.html
```

### Upload Image for Medication

```bash
curl --location --request PUT 'localhost:8080/v1/dispatch/medication/image/upload/1' \
--form 'file=@"/C:/Users/stela/Pictures/336584.jpg"'
```
