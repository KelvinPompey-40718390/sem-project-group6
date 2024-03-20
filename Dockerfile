# Run our jar archive
FROM openjdk:latest
COPY ./target/semProjectGroup6.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semProjectGroup6.jar", "db:3306", "30000"]
