FROM openjdk:11.0.11-jdk
MAINTAINER = "Gabriel Bento"
EXPOSE 8080
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]