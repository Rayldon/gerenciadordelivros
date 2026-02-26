INSERT INTO autor (cod_au, nome)
VALUES (1, 'Robert C. Martin'),
       (2, 'Eric Evans');

INSERT INTO assunto (cod_as, descricao)
VALUES (1, 'Arquitetura'),
       (2, 'Design');

INSERT INTO livro (cod_l, titulo, valor)
VALUES (1, 'Clean Code', 120.00),
       (2, 'Domain-Driven Design', 150.00);

INSERT INTO livro_autor (livro_cod_l, autor_cod_au)
VALUES (1, 1),
       (2, 2);

INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as)
VALUES (1, 1),
       (2, 2);
