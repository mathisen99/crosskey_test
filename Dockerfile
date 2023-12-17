FROM maven:3.8.4-jdk-11-slim as builder

WORKDIR /app

COPY worktest/ worktest/

RUN mvn -f worktest/pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/worktest/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
