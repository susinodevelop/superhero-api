# Usar la imagen base de Maven con JDK 11
FROM maven:3.8.4-openjdk-11

# Crear el directorio de la aplicación
RUN mkdir /app

# Descargar y extraer el archivo zip desde GitHub
ADD https://github.com/susinodevelop/superhero-api/archive/refs/heads/main.zip ./app/main.zip

RUN unzip /app/main.zip -d /app
RUN rm /app/main.zip

# Establece directorio de trabajo para los siguientes comandos
WORKDIR /app/superhero-api-main

# Instalar las dependencias del proyecto y compilar la aplicación
RUN mvn clean install -U

# Exponer el puerto 8080
EXPOSE 8080

# Damos permisos de lectura y ejecución al fichero jar
RUN chmod 755 target/superhero-api.jar

# Ejecutar la aplicación compilada
ENTRYPOINT ["java", "-jar", "target/superhero-api.jar"]