# 📚 Livraria API

API REST para gerenciamento de uma livraria, desenvolvida com **Java e Spring Boot**, aplicando princípios como **SOLID** e persistência de dados com **JPA/Hibernate**.

O projeto tem como objetivo simular o backend de uma livraria, permitindo o gerenciamento de autores, livros, editoras, categorias, clientes e pedidos.

---

## 🚀 Tecnologias

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* MapStruct
* Lombok
* Git / GitHub


A ideia é que o **domínio não dependa diretamente do banco de dados ou de detalhes de infraestrutura**.

---

## 🗄️ Banco de dados

Durante o desenvolvimento, o projeto utiliza o **H2 Database**.

Configuração:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:livraria
    driver-class-name: org.h2.Driver
    username: sa
    password:

  h2:
    console:
      enabled: true
      path: /h2-console

  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### H2 Console

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/h2-console
```

Utilize:

```text
JDBC URL: jdbc:h2:mem:livraria
User Name: sa
Password: [vazio]
```

---

## 🔄 Mapeamento de objetos

O projeto utiliza **MapStruct** para realizar a conversão entre entidades e DTOs.

Exemplo:

```java
@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorResponse toResponse(AutorEntity autorEntity);

    @Mapping(source = "editora.nome", target = "editora")
    LivroResponse toResponse(LivroEntity livroEntity);
}
```

Isso evita a necessidade de realizar conversões manualmente dentro dos controllers ou services.

---

## ⚙️ Funcionalidades

### Autores

* Cadastro de autores
* Consulta de autor
* Listagem de autores
* Atualização de autor
* Exclusão de autor

### Editoras

* Cadastro de editoras
* Consulta de editora
* Listagem de editoras
* Atualização de editoras
* Exclusão de editoras

### Livros

* Cadastro de livros
* Consulta de livros
* Listagem de livros
* Atualização de livros
* Exclusão de livros
* Associação com autores
* Associação com editoras
* Associação com categorias
* Controle de estoque

### Pedidos

* Criação de pedidos
* Associação do pedido ao cliente
* Adição de itens
* Cálculo do valor total
* Controle do status do pedido
* Cancelamento de pedidos
* Controle de estoque

---

## 📌 Tratamento de exceções

As exceções relacionadas às regras de negócio são tratadas na camada de aplicação/domínio.

Exemplos:

```text
LivroNaoEncontradoException
AutorNaoEncontradoException
EstoqueInsuficienteException
PedidoNaoEncontradoException
PedidoNaoPodeSerCanceladoException
```

Enquanto exceções relacionadas à infraestrutura, como falhas de persistência, ficam isoladas nos adapters de saída.

---

## ▶️ Como executar o projeto

### Pré-requisitos

* Java 21+
* Maven 3.9+
* Git

### Clone o projeto

```bash
git clone https://github.com/SEU-USUARIO/livraria-api.git
```

Entre no diretório:

```bash
cd livraria-api
```

Execute:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

---

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

---

## 📡 API

A API segue o padrão REST e utiliza JSON para comunicação.

Exemplo:

```http
GET /autores
```

Resposta:

```json
[
  {
    "id": 1,
    "nome": "Robert C. Martin",
    "livros": [
      {
        "id": 1,
        "titulo": "Clean Code",
        "editora": "Prentice Hall"
      }
    ]
  }
]
```

---

## 🛠️ Próximos passos

Funcionalidades planejadas para evolução do projeto:

* [ ] Spring Security
* [ ] Autenticação com JWT
* [ ] Controle de usuários e permissões
* [ ] Carrinho de compras
* [ ] Integração com gateway de pagamento
* [ ] Integração com serviço de frete
* [ ] Swagger / OpenAPI
* [ ] Testes de integração
* [ ] Testcontainers
* [ ] Docker
* [ ] PostgreSQL
* [ ] CI/CD
* [ ] Observabilidade
* [ ] Logs estruturados

---

## 🎯 Objetivo

Este projeto foi desenvolvido com foco em **estudo e aplicação prática de conceitos de desenvolvimento backend**, principalmente:

* Java
* Spring Boot
* REST APIs
* JPA/Hibernate
* Arquitetura Hexagonal
* SOLID
* Design de software
* DTOs
* MapStruct
* Tratamento de exceções
* Persistência de dados
* Testes automatizados

---

## 👨‍💻 Autor

**Peterson Araujo**

Projeto desenvolvido para estudos e portfólio profissional.
