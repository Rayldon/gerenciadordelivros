-- TABELA AUTOR
CREATE TABLE autor
(
    cod_au BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome   VARCHAR(100) NOT NULL
);

-- TABELA ASSUNTO
CREATE TABLE assunto
(
    cod_as    BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL
);

-- TABELA LIVRO
CREATE TABLE livro
(
    cod_l          BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo         VARCHAR(150)   NOT NULL,
    editora        VARCHAR(100),
    edicao         INT,
    ano_publicacao INT,
    valor          DECIMAL(10, 2) NOT NULL
);

-- LIVRO x AUTOR
CREATE TABLE livro_autor
(
    livro_cod_l  BIGINT NOT NULL,
    autor_cod_au BIGINT NOT NULL,
    PRIMARY KEY (livro_cod_l, autor_cod_au),
    CONSTRAINT fk_livro_autor_livro
        FOREIGN KEY (livro_cod_l) REFERENCES livro (cod_l),
    CONSTRAINT fk_livro_autor_autor
        FOREIGN KEY (autor_cod_au) REFERENCES autor (cod_au)
);

-- LIVRO x ASSUNTO
CREATE TABLE livro_assunto
(
    livro_cod_l    BIGINT NOT NULL,
    assunto_cod_as BIGINT NOT NULL,
    PRIMARY KEY (livro_cod_l, assunto_cod_as),
    CONSTRAINT fk_livro_assunto_livro
        FOREIGN KEY (livro_cod_l) REFERENCES livro (cod_l),
    CONSTRAINT fk_livro_assunto_assunto
        FOREIGN KEY (assunto_cod_as) REFERENCES assunto (cod_as)
);

-- VIEW DO RELATORIO
CREATE VIEW vw_relatorio_autor AS
SELECT a.cod_au      AS autor_id,
       a.nome        AS autor_nome,
       l.cod_l       AS livro_id,
       l.titulo      AS livro_titulo,
       l.valor       AS livro_valor,
       s.cod_as      AS assunto_id,
       s.descricao   AS assunto_descricao
FROM autor a
         JOIN livro_autor la ON la.autor_cod_au = a.cod_au
         JOIN livro l ON l.cod_l = la.livro_cod_l
         JOIN livro_assunto ls ON ls.livro_cod_l = l.cod_l
         JOIN assunto s ON s.cod_as = ls.assunto_cod_as;