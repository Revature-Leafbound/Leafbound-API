# Declare the base image - here is a light weight JDK 8 environment setup.
FROM openjdk:8-jdk-alpine

# This sets the environmental variable.
ENV APP_HOME=/usr/app/

# This is the directory where the output of CMD should run.
WORKDIR $APP_HOME

# This copies the file or a directory to the container’s directory.
COPY /target/leafbound-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 of the container.
EXPOSE 8080

# This is the command that runs the application.
CMD ["java", "-jar", "app.jar"]