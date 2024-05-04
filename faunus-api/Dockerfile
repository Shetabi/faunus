FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal
WORKDIR /app
COPY ./target/faunus-api-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
