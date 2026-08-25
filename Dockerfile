FROM eclipse-temurin:17-jre
LABEL maintainer="setec.com"
COPY target/spring-db-0.0.1-SNAPSHOT.jar myapp/app.jar
WORKDIR /myapp
ENTRYPOINT ["java","-jar","app.jar"]