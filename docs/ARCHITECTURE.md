## Visao arquitetural
A aplicacao segue estilo de Arquitetura Hexagonal (Ports and Adapters), com dependencia apontando para o dominio.

Regra principal:
- `domain` nao depende de Spring
- `application` depende de portas do dominio
- `infrastructure` implementa portas e integra REST/JPA

## Camadas
- `domain`
  - modelos (`Livro`, `Autor`, `Assunto`)
  - excecoes de negocio (`RegraNegocioException`)
  - portas (`LivroRepository`, `AutorRepository`, `AssuntoRepository`)
- `application`
  - casos de uso de criar e listar
- `infrastructure.persistence`
  - entities JPA
  - repositories JPA
  - adapters de persistencia
- `infrastructure.web`
  - controllers REST
  - DTOs de entrada/saida
  - mappers web
  - exception handler global

## Decisoes tecnicas relevantes
- Validacao de regras de negocio no dominio
- Controllers sem logica de regra (somente orquestracao HTTP)
- Mapeamento DTO <-> dominio isolado em mappers dedicados
- Tratamento de erro padronizado em `GlobalExceptionHandler`

## Trade-offs
- Projeto privilegia clareza de arquitetura e testabilidade
- Nao ha ainda camada de seguranca/autenticacao
- Endpoints implementam somente criar e listar (escopo atual)
