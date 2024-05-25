FROM maven:3.8.4-openjdk-11

COPY . /app

WORKDIR /app

RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/superhero-api.jar"]