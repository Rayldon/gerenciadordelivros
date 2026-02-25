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

    public Livro(String titulo, BigDecimal valor, Set<Autor> autores) {
        if (autores == null || autores.isEmpty()) {
            throw new RegraNegocioException("Livro deve ter ao menos um autor.");
        }

        this.titulo = titulo;
        this.valor = valor;
        this.autores = new HashSet<>(autores);
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
}
