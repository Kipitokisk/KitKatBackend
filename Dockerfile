FROM maven:3.9.9-eclipse-temurin-17 AS stage1
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /opt/demo
COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline -B
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-alpine
WORKDIR /opt/demo
ARG JAR_FILE="opt/demo/target/*.jar"
COPY --from=stage1 /opt/demo/target/*.jar /opt/demo/app.jar
ENTRYPOINT ["java", "-jar", "/opt/demo/app.jar"]



#VOLUME /tmp
#ENTRYPOINT ["java","-jar","/app.jar"]