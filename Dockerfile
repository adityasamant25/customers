FROM openjdk:21-slim
COPY target/customers-1.0.0-SNAPSHOT.jar customers-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customers-1.0.0-SNAPSHOT.jar"]