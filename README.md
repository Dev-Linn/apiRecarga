# 📱 API de Recarga de Celular

Sistema completo de API para recarga de celular com processamento assíncrono usando Spring Boot, PostgreSQL e RabbitMQ.

## 🚀 Funcionalidades

### ✅ **Implementadas:**
- **Cadastro de Clientes** - CRUD completo com validações
- **Métodos de Pagamento** - Gerenciamento de cartões, PIX, boleto
- **Operadoras** - Vivo, TIM, Claro, Oi, Algar, Sercomtel
- **Recargas** - Sistema completo com processamento assíncrono
- **RabbitMQ** - Processamento em background
- **OpenAPI/Swagger** - Documentação automática da API
- **Docker** - Containerização completa
- **PostgreSQL** - Banco de dados relacional
- **Validações** - Bean Validation em todas as entidades

## 🏗️ Arquitetura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controllers   │    │    Services     │    │  Repositories   │
│   (REST APIs)   │───▶│ (Business Logic)│───▶│  (Data Access)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   OpenAPI       │    │   RabbitMQ      │    │   PostgreSQL    │
│   (Swagger)     │    │  (Messaging)    │    │   (Database)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Spring AMQP (RabbitMQ)**
- **PostgreSQL**
- **Docker & Docker Compose**
- **OpenAPI 3 (Swagger)**
- **Bean Validation**
- **Maven**

## 📋 Pré-requisitos

- Java 21+
- Maven 3.6+
- Docker & Docker Compose
- Git

## 🚀 Como Executar

### **Opção 1: Docker Compose (Recomendado)**

```bash
# 1. Clone o repositório
git clone <seu-repositorio>
cd apiRecarga

# 2. Execute com Docker Compose
docker-compose up -d

# 3. Acesse a aplicação
# API: http://localhost:8080
# Swagger: http://localhost:8080/swagger-ui.html
# RabbitMQ Management: http://localhost:15672 (guest/guest)
```

### **Opção 2: Execução Local**

```bash
# 1. Inicie PostgreSQL e RabbitMQ
docker-compose up -d postgres rabbitmq

# 2. Compile e execute
mvn clean compile
mvn spring-boot:run

# 3. Acesse a aplicação
# API: http://localhost:8080
# Swagger: http://localhost:8080/swagger-ui.html
```

## 📚 Documentação da API

### **Swagger UI**
Acesse: http://localhost:8080/swagger-ui.html

---

