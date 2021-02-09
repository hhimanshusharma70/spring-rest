# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
ENV HOME=/home/app
RUN mkdir -p $HOME

ADD pom.xml $HOME

RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
ADD . $HOME
RUN ["mvn", "package"]
EXPOSE 8282

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/spring-rest-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-rest-0.0.1-SNAPSHOT.jar"]