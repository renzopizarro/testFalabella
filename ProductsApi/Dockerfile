
FROM maven:3.6.0-jdk-8 AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/productsApi-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]