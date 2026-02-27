## Banco de dados
Banco em memoria H2 para execucao local e testes.

## Script de schema
- `src/main/resources/scripts/schema.sql`

Tabelas:
- `autor`
- `assunto`
- `livro`
- `livro_autor`
- `livro_assunto`

View:
- `vw_relatorio_autor`

## Configuracao principal
- `src/main/resources/application.properties`

## Configuracao de testes
- `src/test/resources/application.properties`
- `src/test/resources/application-e2e.properties`

## Scripts auxiliares de teste
- `src/test/resources/scripts/test-data.sql` (massa para consulta)
- `src/test/resources/scripts/clean.sql` (limpeza antes de cenarios e2e)
