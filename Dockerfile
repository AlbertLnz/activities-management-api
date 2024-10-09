# FROM openjdk:17-jdk-slim
# ARG JAR_FILE=target/activities-management-api-1.0.0.jar
# COPY ${JAR_FILE} activities_management_api.jar
# EXPOSE 4000
# ENTRYPOINT ["java", "-jar", "activities_management_api.jar"]


FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY src/main/resources/application.sample.yml /home/app/src/main/resources/application.yml
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/activities_management_api.jar"]