FROM openjdk:17
EXPOSE 8080
WORKDIR /app
RUN mkdir -p /app/
ADD applications/app-service/build/libs/cliente.jar /app/cliente.jar
ENTRYPOINT ["java", "-jar", "/app/cliente.jar"]