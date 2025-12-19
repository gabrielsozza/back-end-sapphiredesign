# --- ESTÁGIO 1: CONSTRUÇÃO (BUILD) ---
# Usamos uma imagem que JÁ TEM o Maven instalado (para não depender do seu mvnw)
FROM maven:3.8.5-openjdk-17 AS build

# Copia os arquivos do projeto para dentro do Docker
COPY . .

# Roda o comando de construção usando o Maven do Docker (mvn, não ./mvnw)
RUN mvn clean package -DskipTests

# --- ESTÁGIO 2: EXECUÇÃO (RUN) ---
# Usamos uma imagem leve só com Java para rodar o site
FROM eclipse-temurin:17-jdk-alpine

# Copia o arquivo .jar que foi criado no estágio anterior
COPY --from=build /target/*.jar app.jar

# Configura a porta
ENV SERVER_PORT=10000
EXPOSE 10000

# Roda o aplicativo
CMD ["java", "-jar", "app.jar"]
