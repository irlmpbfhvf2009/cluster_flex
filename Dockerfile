FROM openjdk:11

WORKDIR /app

COPY /eureka-server/target/eureka-server-1.0.1.jar /app/eureka-server.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/eureka-server.jar"]
