FROM openjdk:11
VOLUME /tmp
COPY target/football-api-0.0.1-SNAPSHOT.jar football-api.jar
ENTRYPOINT ["java","-jar","/football-api.jar"]
EXPOSE 8080