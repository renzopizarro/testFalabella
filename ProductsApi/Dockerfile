
FROM maven:3.6.0-jdk-8-alpine as builder
COPY  . /root/app/
WORKDIR /root/app
RUN mvn install

FROM openjdk:8-jdk-alpine as jdk8
EXPOSE 8080
COPY --from=builder /root/app/ /home/app/
WORKDIR /home/app
ENTRYPOINT ["java","-jar", "-Xmx15m", "./target/productsApi-1.0.0.jar"]
