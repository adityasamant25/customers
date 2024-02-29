FROM openjdk:21-slim
COPY target/customers-1.0.2-exec.jar /opt/app/
EXPOSE 8081
CMD ["java", "-showversion", "-jar", "/opt/app/customers-1.0.2-exec.jar"]