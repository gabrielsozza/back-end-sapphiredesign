# 1. Usa uma imagem leve do Java 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Copia seus arquivos para dentro do servidor
COPY . .

# 3. Dá permissão para o Maven trabalhar
RUN chmod +x mvnw

# 4. Fabrica o aplicativo (Build)
RUN ./mvnw clean package -DskipTests

# 5. Liga o servidor na porta certa
ENV SERVER_PORT=10000
EXPOSE 10000

# 6. O comando final que roda o site
CMD ["java", "-jar", "target/*.jar"]