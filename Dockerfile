# Estágio 1: Build (Compilação)
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o JAR que o Maven gerou (com o nome novo que você configurou)
COPY --from=build /app/target/diario-glicemia-*.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando para rodar já avisando que o perfil é o de PROD
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]