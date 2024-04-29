FROM eclipse-temurin:21.0.2_13-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean install package

FROM eclipse-temurin:21.0.2_13-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/rest-with-jdbc.jar /app

EXPOSE 8080

CMD ["java", "-jar","/app/rest-with-jdbc.jar"]
