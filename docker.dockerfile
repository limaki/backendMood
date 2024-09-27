# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/crudUsers-0.0.1-SNAPSHOT.jar app.jar



# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
