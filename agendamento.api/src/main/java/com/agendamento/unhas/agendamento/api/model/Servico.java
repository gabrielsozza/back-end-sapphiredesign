package com.agendamento.unhas.agendamento.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Ex: Unha de Gel

    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco; // Usamos BigDecimal para dinheiro (é mais preciso que double)

    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos; // Ex: 60, 90

    // Construtor vazio
    public Servico() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
}