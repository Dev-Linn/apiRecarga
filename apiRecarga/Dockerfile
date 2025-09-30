# Use OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivo de configuração do Maven primeiro (para cache de dependências)
COPY pom.xml .

# Download das dependências (cache layer)
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src src

# Compilar e gerar JAR
RUN mvn clean package -DskipTests

# Expor porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "target/apiRecarga-0.0.1-SNAPSHOT.jar"]
