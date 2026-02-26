package com.projeto.gerenciadordelivros.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_l")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "autor_cod_au")
    )
    private Set<AutorEntity> autores = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "assunto_cod_as")
    )
    private Set<AssuntoEntity> assuntos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<AutorEntity> getAutores() {
        return autores;
    }

    public void setAutores(Set<AutorEntity> autores) {
        this.autores = autores;
    }

    public Set<AssuntoEntity> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(Set<AssuntoEntity> assuntos) {
        this.assuntos = assuntos;
    }
}
