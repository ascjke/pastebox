FROM openjdk:14-alpine
MAINTAINER Zakhar Borisov
COPY target/pastebox-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java", "-jar", "/pastebox.jar"]