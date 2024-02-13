FROM openjdk:21-slim
COPY target/customers-0.8.0.jar customers-0.8.0.jar
ENTRYPOINT ["java","-jar","/customers-0.8.0.jar"]