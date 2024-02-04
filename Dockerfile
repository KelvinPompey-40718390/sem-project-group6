# Run our jar archive
FROM openjdk:latest
COPY ./target/seProjectGroup6-0.1-alpha-1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seProjectGroup6-0.1-alpha-1-jar-with-dependencies.jar"]
