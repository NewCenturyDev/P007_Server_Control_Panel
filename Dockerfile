FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY build/servercp-0.1.0-SNAPSHOT.jar app.jar
COPY h2db h2db
ENTRYPOINT ["java","-jar","/app.jar"]
