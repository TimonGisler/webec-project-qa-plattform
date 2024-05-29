# run mvnw install before building the image
FROM amazoncorretto:21.0.3
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Entrypoint will be run for every docker build, i will execute the app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


EXPOSE 8080