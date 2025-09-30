package com.example.apiRecarga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "metodos_pagamento")
public class MetodoPagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @NotBlank(message = "Tipo de pagamento é obrigatório")
    @Column(name = "tipo_pagamento", nullable = false)
    private String tipoPagamento; // CARTAO_CREDITO, PIX, BOLETO
    
    @NotBlank(message = "Descrição é obrigatório")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descricao; // "Cartão Visa **** 1234"
    
    @NotNull(message = "Dados do pagamento são obrigatórios")
    @Size(max = 1000, message = "Dados devem ter no máximo 1000 caracteres")
    @Column(name = "dados_pagamento", nullable = false, length = 1000)
    private String dadosPagamento; // JSON com dados específicos
    
    @Column(name = "padrao")
    private Boolean padrao = false; // Método padrão do cliente
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    public MetodoPagamento() {}
    
    public MetodoPagamento(Cliente cliente, String tipoPagamento, String descricao, String dadosPagamento) {
        this.cliente = cliente;
        this.tipoPagamento = tipoPagamento;
        this.descricao = descricao;
        this.dadosPagamento = dadosPagamento;
    }
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(String dadosPagamento) { this.dadosPagamento = dadosPagamento; }
    
    public Boolean getPadrao() { return padrao; }
    public void setPadrao(Boolean padrao) { this.padrao = padrao; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
