# API REST com Autenticação via API Key

API REST de produtos com autenticação via API Key usando Spring Boot e Spring Security.

---

## Como rodar o projeto

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Passos
git clone https://github.com/unilopers/rest-controller.git
cd rest-controller
./mvnw spring-boot:run

A API estará disponível em: http://localhost:8080

---

## Como autenticar

Todas as requisições para /produtos precisam do header:

X-API-Key: sk-example-key-12345

Chaves disponíveis para teste:
- sk-example-key-12345
- sk-test-key-67890

---

## Como testar

### Sem API Key -> 401
curl http://localhost:8080/produtos

### Com API Key correta -> 200
curl -H "X-API-Key: sk-example-key-12345" http://localhost:8080/produtos

### Com API Key errada -> 401
curl -H "X-API-Key: chave-errada" http://localhost:8080/produtos

---

## Estrutura do projeto

src/main/java/com/example/restcontroller/
├── RestControllerApplication.java
├── DataLoader.java
├── controller/
│   └── ProdutoController.java
├── model/
│   └── Produto.java
└── security/
    ├── ApiKey.java
    ├── ApiKeyRepository.java
    ├── ApiKeyAuthentication.java
    ├── ApiKeyAuthenticationFilter.java
    ├── ApiKeyAuthenticationProvider.java
    └── SecurityConfiguration.java

---

## Equipe

| Nome | Papel |
|------|-------|
| Pedro Lucas | Tech Lead |
| Nicholas Kaskanlian | Dev Backend |
| Caio | Dev Backend |
| Joao Pedro Burgos | Dev Backend |
| Fernando Mantovani | Documentacao |