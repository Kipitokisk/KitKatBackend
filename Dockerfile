FROM maven:3.9.9-eclipse-temurin-17-alpine AS stage1
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

WORKDIR /opt/demo

COPY pom.xml .

RUN mvn dependency:go-offline -B


COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true -T 1C

FROM openjdk:17-alpine

WORKDIR /opt/demo
ARG JAR_FILE="opt/demo/target/*.jar"
COPY --from=stage1 /${JAR_FILE} /opt/demo/app.jar

ENTRYPOINT ["java", "-jar", "/opt/demo/app.jar"]


ENV APP_VERSION=${VERSION}

#VOLUME /tmp
#ENTRYPOINT ["java","-jar","/app.jar"]