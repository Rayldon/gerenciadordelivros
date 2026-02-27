## Linguagem do dominio

### Livro
Representa uma obra cadastrada na biblioteca.

Campos usados no dominio:
- titulo
- valor
- autores
- assuntos

### Autor
Representa o nome do autor do livro.

### Assunto
Representa a categoria/tema do livro.

## Regras de negocio implementadas

### Autor
- nome obrigatorio
- nome nao pode ser vazio ou em branco

### Assunto
- descricao obrigatoria
- descricao nao pode ser vazia ou em branco

### Livro
- titulo obrigatorio
- valor obrigatorio e nao negativo
- precisa de pelo menos 1 autor
- nao permite autores duplicados (comparacao por nome normalizado)
- precisa de pelo menos 1 assunto

## Onde as regras vivem
As validacoes estao nos construtores das classes de dominio, lancando `RegraNegocioException` quando necessario.
