FROM openjdk:8-jdk-alpine
COPY application.jar app.jar
ARG PROFILE
ENV SPRING_PROFILES_ACTIVE=$PROFILE
ENTRYPOINT ["java","-jar", "/app.jar"]
EXPOSE 8080
