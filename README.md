FoodHub API

Sobre o Projeto

O FoodHub é uma API que permite o cadastro, autenticação e gerenciamento de usuários do tipo:

Clientes
Donos de restaurante

A aplicação foi construída com foco em:

Separação de responsabilidades (Controller, Service, Repository)
Segurança com autenticação
Padronização de respostas e tratamento de erros
Facilidade de deploy com Docker

---

Tecnologias Utilizadas

- Java 17
- Spring Boot 4.0.5
- Spring Security
- Spring Data JP
- MySQL 8.0
- Lombok
- SpringDoc OpenAPI (Swagger UI)
- Docker & Docker Compose

---

# Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- Docker Desktop
- Para rodar localmente sem Docker:
  - Java 17+
  - Maven 3.9+
  - MySQL 8.0

---

## Rodando com Docker 

Com o Docker Desktop aberto, execute na raiz do projeto:

Bash
docker-compose up --build

Isso irá:
1. Subir o banco de dados MySQL automaticamente
2. Compilar e iniciar a aplicação Spring Boot

A API estará disponível em:`http://localhost:8080`

> Na primeira vez use `--build`. Nas próximas, basta `docker-compose up`.

Para parar os containers:
bash
docker-compose down


## Documentação da API (Swagger)

Com a aplicação rodando, acesse a documentação interativa:
`http://localhost:8080/swagger-ui/index.html`

---

## Endpoints

### Autenticação — `/auth`

| Método | Endpoint      | Descrição              | Autenticação |
|--------|---------------|------------------------|--------------|
| POST   | `/auth/login` | Realiza login na API   | Não          |

**Body (POST /auth/login):**
```json
{
  "login": "seu_login",
  "senha": "sua_senha"
}
```

---

### 👤 Clientes — `/clientes`

| Método | Endpoint                  | Descrição                     | Autenticação |
|--------|---------------------------|-------------------------------|--------------|
| GET    | `/clientes/buscar?nome=`  | Busca clientes pelo nome      | Sim          |
| POST   | `/clientes`               | Cadastra um novo cliente      | Não          |
| PUT    | `/clientes/{id}`          | Atualiza dados do cliente     | Sim          |
| DELETE | `/clientes/{id}`          | Remove um cliente             | Sim          |
| PATCH  | `/clientes/{id}/senha`    | Altera a senha do cliente     | Sim          |

**Body (POST /clientes):**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "login": "joaosilva",
  "senha": "senha123",
  "endereco": "Rua das Flores, 123"
}
```

**Body (PUT /clientes/{id}):**
```json
{
  "nome": "João Silva Atualizado",
  "endereco": "Av. Paulista, 1000"
}
```

**Body (PATCH /clientes/{id}/senha):**
```json
{
  "senhaAtual": "senha123",
  "novaSenha": "novaSenha456"
}
```

---

### 🏪 Donos de Restaurante — `/donos`

| Método | Endpoint                | Descrição                         | Autenticação |
|--------|-------------------------|-----------------------------------|--------------|
| GET    | `/donos/buscar?nome=`   | Busca donos pelo nome             | Sim          |
| POST   | `/donos`                | Cadastra um novo dono             | Não          |
| PUT    | `/donos/{id}`           | Atualiza dados do dono            | Sim          |
| DELETE | `/donos/{id}`           | Remove um dono                    | Sim          |
| PATCH  | `/donos/{id}/senha`     | Altera a senha do dono            | Sim          |

**Body (POST /donos):**
```json
{
  "nome": "Maria Santos",
  "email": "maria@restaurante.com",
  "login": "mariasantos",
  "senha": "senha123",
  "endereco": "Rua do Comércio, 45"
}
```

---

## 🗄️ Estrutura do Projeto

```
foodhub/
├── controllers/       # Endpoints da API
├── services/          # Regras de negócio
├── entities/          # Entidades JPA
├── dtos/              # Objetos de transferência
├── repositories/      # Acesso ao banco
├── config/            # Segurança e configurações
├── exceptions/        # Tratamento global de erros
```

---

## 👨‍💻 Autor

Desenvolvido por André Brusso.
