FROM openjdk:20
VOLUME /tmp
ADD /target/vcharugin-0.0.1-SNAPSHOT.jar vcharugin-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/vcharugin-0.0.1-SNAPSHOT.jar"]
