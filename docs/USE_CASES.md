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

## Contratos de persistencia
- `LivroRepository`
- `AutorRepository`
- `AssuntoRepository`

As implementacoes concretas ficam em `infrastructure.persistence.repository`.

## Endpoints x use cases
- `POST /livros` -> `CriarLivroUseCase`
- `GET /livros` -> `ListarLivrosUseCase`
- `POST /autores` -> `CriarAutorUseCase`
- `GET /autores` -> `ListarAutoresUseCase`
- `POST /assuntos` -> `CriarAssuntoUseCase`
- `GET /assuntos` -> `ListarAssuntosUseCase`
