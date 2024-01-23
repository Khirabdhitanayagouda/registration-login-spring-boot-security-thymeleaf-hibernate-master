FROM openjdk:11
WORKDIR /app
COPY target/registration-login-spring-boot-security-thymeleaf-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
CMD ["java","-jar","app.jar"]
