FROM maven:3.9.9-amazoncorretto-23 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM openjdk:25-slim
RUN mkdir /app
COPY --from=build /project/target/Managment-0.0.1-SNAPSHOT.jar /app/java-application.jar
WORKDIR /app
CMD ["java", "-jar", "java-application.jar"]

