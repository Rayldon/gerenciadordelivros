# Gerenciador de Livros
Projeto backend em Java/Spring Boot para cadastro e consulta de livros, autores e assuntos.

## Frontend
- Repositorio web (Angular + Bootstrap): https://github.com/Rayldon/gerenciadordelivros-web

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
- JasperReports (geracao de PDF)
- Lombok
- JUnit 5 + Mockito

## Banco de dados
- O projeto usa H2 em memoria por padrao (ambiente local/testes).
- A configuracao pode ser alterada para outro banco relacional (ex: PostgreSQL, MySQL, SQL Server) ajustando as propriedades de datasource/JPA.

## Endpoints implementados
- `POST /livros`
- `GET /livros?page=0&size=10`
- `PUT /livros/{id}`
- `DELETE /livros/{id}`
- `POST /autores`
- `GET /autores`
- `PUT /autores/{id}`
- `DELETE /autores/{id}`
- `POST /assuntos`
- `GET /assuntos`
- `PUT /assuntos/{id}`
- `DELETE /assuntos/{id}`
- `GET /relatorios/autores` (PDF agrupado por autor)

## Como executar
Pre requisitos:
- JDK 21
- `JAVA_HOME` configurado
- Lombok habilitado na IDE (annotation processing)

Comandos:
```bash
./mvnw spring-boot:run
```
No Windows PowerShell:
```powershell
.\mvnw spring-boot:run
```

API local:
- `http://localhost:8080`
- CORS liberado para `http://localhost:4200`

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
- Relatorio PDF e gerado a partir da view `vw_relatorio_autor`
- `GlobalExceptionHandler` padroniza respostas de erro
- Lombok reduz boilerplate em controllers/usecases/repositories/entidades mantendo regras de dominio explicitas
- Testes cobrem regra de dominio, repositorio, controllers (MockMvc) e 1 fluxo e2e completo

## Lombok na IDE (IntelliJ)
1. Instale o plugin `Lombok` (Settings > Plugins).
2. Ative `Enable annotation processing` em:
`Settings > Build, Execution, Deployment > Compiler > Annotation Processors`.
3. Reimporte o projeto Maven se necessario.

## Estrutura de documentacao
- [Arquitetura](docs/ARCHITECTURE.md)
- [Dominio](docs/DOMAIN.md)
- [Casos de Uso](docs/USE_CASES.md)
- [Erros e Excecoes](docs/EXCEPTIONS.md)
- [Estrategia de Testes](docs/TEST_STRATEGY.md)
- [Banco de Dados](docs/DATABASE.md)
- [Execucao local](docs/DEPLOY.md)
