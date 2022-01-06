FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY build/servercp-0.1.1-SNAPSHOT.jar app.jar
RUN mkdir -p /h2db
ENTRYPOINT ["java","-jar","/app.jar"]
