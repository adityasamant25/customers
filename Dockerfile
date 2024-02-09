FROM openjdk:21-slim
COPY target/customers-2.0.0-SNAPSHOT.jar customers-2.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customers-2.0.0-SNAPSHOT.jar"]
