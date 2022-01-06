FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY build/servercp-0.1.1-SNAPSHOT.jar app.jar
COPY h2db h2db
ENTRYPOINT ["java","-jar","/app.jar"]
