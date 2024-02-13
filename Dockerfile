FROM openjdk:21-slim
COPY target/customers-0.9.0-exec.jar customers-0.9.0.jar
ENTRYPOINT ["java","-jar","/customers-0.9.0.jar"]
