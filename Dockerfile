# syntax=docker/dockerfile:1
FROM openjdk:11-jre-slim
MAINTAINER stevedevblog.com
COPY target/app-0.1.0.jar app-0.1.0.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/app-0.1.0.jar"]