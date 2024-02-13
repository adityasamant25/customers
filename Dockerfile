FROM openjdk:21-slim
COPY target/customers-1.0.0-exec.jar customers-1.0.0.jar
ENTRYPOINT ["java","-jar","/customers-1.0.0.jar"]
