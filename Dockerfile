FROM openjdk:20
ARG JAR_FILE=target/vcharugin-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myapp.jar"]
