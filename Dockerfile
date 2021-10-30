FROM openjdk:11-jre-slim
MAINTAINER stevedevblog.com
COPY target/mvp-0.0.2.jar mvp-0.0.2.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/mvp-0.0.2.jar"]