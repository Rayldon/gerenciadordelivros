package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Livro {

    private Long id;
    private String titulo;
    private String editora;
    private Integer edicao;
    private Integer anoPublicacao;
    private BigDecimal valor;
    private Set<Autor> autores;
    private Set<Assunto> assuntos;

    public Livro(String titulo, BigDecimal valor, Set<Autor> autores, Set<Assunto> assuntos) {
        validar(titulo, valor, autores, assuntos);
        this.titulo = titulo;
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
        this.autores = new HashSet<>(autores);
        this.assuntos = new HashSet<>(assuntos);
    }

    public Livro(Long id, String titulo, BigDecimal valor, Set<Autor> autores, Set<Assunto> assuntos) {
        validar(titulo, valor, autores, assuntos);
        this.id = id;
        this.titulo = titulo;
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
        this.autores = new HashSet<>(autores);
        this.assuntos = new HashSet<>(assuntos);
    }

    private void validar(String titulo, BigDecimal valor, Set<Autor> autores, Set<Assunto> assuntos) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new RegraNegocioException("Titulo do livro e obrigatorio.");
        }
        if (valor == null || valor.signum() < 0) {
            throw new RegraNegocioException("Valor do livro nao pode ser negativo.");
        }
        if (autores == null || autores.isEmpty()) {
            throw new RegraNegocioException("Livro deve ter ao menos um autor.");
        }
        long quantidadeNomesAutores = autores.stream()
                .map(Autor::getNome)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet())
                .size();
        if (quantidadeNomesAutores != autores.size()) {
            throw new RegraNegocioException("Livro nao pode ter autores duplicados.");
        }
        if (assuntos == null || assuntos.isEmpty()) {
            throw new RegraNegocioException("Livro deve ter ao menos um assunto.");
        }
    }

    public Set<Autor> getAutores() {
        return Collections.unmodifiableSet(autores);
    }

    public Set<Assunto> getAssuntos() {
        return Collections.unmodifiableSet(assuntos);
    }
}
