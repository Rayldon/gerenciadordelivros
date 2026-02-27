# Gerenciador de Livros
Projeto backend em Java/Spring Boot para cadastro e consulta de livros, autores e assuntos.

## Objetivo tecnico
Demonstrar:
- modelagem de dominio com regras explicitas
- arquitetura em camadas com isolamento de responsabilidades
- API REST com DTOs, mapeadores e tratamento centralizado de erros
- estrategia de testes (unitario, slice e e2e)

## Stack
- Java 21
- Spring Boot 4.0.3
- Spring Web MVC
- Spring Data JPA
- H2 (memoria)
- JUnit 5 + Mockito

## Endpoints implementados
- `POST /livros`
- `GET /livros`
- `POST /autores`
- `GET /autores`
- `POST /assuntos`
- `GET /assuntos`

## Como executar
Pre requisitos:
- JDK 21
- `JAVA_HOME` configurado

Comandos:
```bash
./mvnw spring-boot:run
```
No Windows PowerShell:
```powershell
.\mvnw spring-boot:run
```

## Como rodar testes
Suite completa:
```powershell
.\mvnw test
```

Somente e2e:
```powershell
.\mvnw -Dtest=LivroE2ETest test
```

## Pontos importantes para avaliacao tecnica
- Dominio sem dependencia de Spring (regras no centro do sistema)
- Casos de uso dependem de portas (`domain.port`) e nao de JPA direto
- Adapters de persistencia e web ficam na borda da aplicacao
- `GlobalExceptionHandler` padroniza respostas de erro
- Testes cobrem regra de dominio, repositorio, controllers (MockMvc) e 1 fluxo e2e completo

## Estrutura de documentacao
- [Arquitetura](docs/ARCHITECTURE.md)
- [Dominio](docs/DOMAIN.md)
- [Casos de Uso](docs/USE_CASES.md)
- [Erros e Excecoes](docs/EXCEPTIONS.md)
- [Estrategia de Testes](docs/TEST_STRATEGY.md)
- [Banco de Dados](docs/DATABASE.md)
- [Execucao local](docs/DEPLOY.md)
