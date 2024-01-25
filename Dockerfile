FROM openjdk:21-slim
COPY target/customers-0.0.1-SNAPSHOT.jar customers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customers-0.0.1-SNAPSHOT.jar"]