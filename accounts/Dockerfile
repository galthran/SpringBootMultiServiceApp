FROM openjdk:17-jdk-slim

MAINTAINER galthran80

COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts.jar

ENTRYPOINT ["java", "-jar", "/accounts.jar"]

EXPOSE 8091