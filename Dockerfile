# Etapa 1: Build da aplicação com Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copia todos os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build do projeto sem executar testes
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar o JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Define a porta que o Render vai expor
ENV PORT=8080
EXPOSE 8080

# Variável para Spring Boot usar a porta dinâmica do Render
ENV SERVER_PORT=${PORT}

# Comando para iniciar o app
ENTRYPOINT ["java","-jar","app.jar"]

