# 💈 Barbearia Web API - Projeto de Estudos

Este projeto é uma aplicação web backend para gerenciamento de uma barbearia, desenvolvido com foco em estudo e prática de Java com Spring Boot.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security (com JWT)** (em breve)
- **Maven**
- **MySQL 8.x** (Banco de dados)
- **Lombok**
- **Jakarta Persistence (JPA)**
- **Hibernate**
- **Postman / Insomnia** (para testes de API)

---

## 📌 Funcionalidades previstas (Roadmap do Projeto)

- ✅ Modelagem de entidades
- ✅ Criação dos repositórios (Camada de acesso a dados)
- ✅ Configuração de autenticação e segurança com JWT
- ⏳ Camada de serviços e controladores (REST API)
- ⏳ Validações, regras de negócio e DTOs
- ⏳ Documentação com Swagger
- ⏳ Relatórios (Agendamentos, Vendas, etc)

---

## ⚙️ Como Rodar o Projeto Localmente

### 1. Pré-requisitos:

- Java 17 instalado
- MySQL rodando (crie um banco com o nome: `barbershop_db`)
- Maven instalado

### 2. Configuração do `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/barbershop_db
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

````

### 3. Build e execução:

````
mvn clean install
mvn spring-boot:run
````

## 🎯 Status Atual do Projeto

    🚧 Em construção...
