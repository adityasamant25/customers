FROM bellsoft/liberica-runtime-container:jdk-21-slim-musl
COPY target/customers-1.0.1-exec.jar /opt/app/
EXPOSE 8081
CMD ["java", "-showversion", "-jar", "/opt/app/customers-1.0.1-exec.jar"]