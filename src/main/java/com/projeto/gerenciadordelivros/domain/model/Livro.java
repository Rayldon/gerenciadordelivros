package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new RegraNegocioException("Titulo do livro e obrigatorio.");
        }
        if (valor == null || valor.signum() < 0) {
            throw new RegraNegocioException("Valor do livro nao pode ser negativo.");
        }
        if (autores == null || autores.isEmpty()) {
            throw new RegraNegocioException("Livro deve ter ao menos um autor.");
        }
        if (assuntos == null || assuntos.isEmpty()) {
            throw new RegraNegocioException("Livro deve ter ao menos um assunto.");
        }

        this.titulo = titulo;
        this.valor = valor;
        this.autores = new HashSet<>(autores);
        this.assuntos = new HashSet<>(assuntos);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<Autor> getAutores() {
        return Collections.unmodifiableSet(autores);
    }

    public Set<Assunto> getAssuntos() {
        return Collections.unmodifiableSet(assuntos);
    }
}
