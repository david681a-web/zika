# Etapa 1: Build da aplicação
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Define a porta que o Render usará
ENV PORT=8080
EXPOSE 8080

# Comando para iniciar o app
CMD ["java", "-jar", "app.jar"]
