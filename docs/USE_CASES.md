## Casos de uso implementados

### Livro
- `CriarLivroUseCase`
- `ListarLivrosUseCase`

### Autor
- `CriarAutorUseCase`
- `ListarAutoresUseCase`

### Assunto
- `CriarAssuntoUseCase`
- `ListarAssuntosUseCase`

### Relatorio
- `GerarRelatorioAutoresUseCase`

## Contratos de persistencia
- `LivroRepository`
- `AutorRepository`
- `AssuntoRepository`
- `RelatorioAutoresPort`

As implementacoes concretas ficam em `infrastructure.persistence.repository` e `infrastructure.report`.

## Endpoints x use cases
- `POST /livros` -> `CriarLivroUseCase`
- `GET /livros` -> `ListarLivrosUseCase`
- `POST /autores` -> `CriarAutorUseCase`
- `GET /autores` -> `ListarAutoresUseCase`
- `POST /assuntos` -> `CriarAssuntoUseCase`
- `GET /assuntos` -> `ListarAssuntosUseCase`
- `GET /relatorios/autores` -> `GerarRelatorioAutoresUseCase`
