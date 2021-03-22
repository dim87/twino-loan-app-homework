#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/twino-loan-app-homework-0.0.1-SNAPSHOT.jar /usr/local/lib/twino-loan-app-homework.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/usr/local/lib/twino-loan-app-homework.jar"]
