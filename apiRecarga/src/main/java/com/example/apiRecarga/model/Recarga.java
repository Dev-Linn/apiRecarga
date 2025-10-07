package com.example.apiRecarga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recargas")
public class Recarga {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @NotBlank(message = "Número do celular é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Número deve estar no formato (XX)XXXXX-XXXX")
    @Column(name = "numero_celular", nullable = false, length = 15)
    private String numeroCelular;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pagamento_id")
    private MetodoPagamento metodoPagamento;
    
    @Column(name = "tipo_pagamento", nullable = false)
    private String tipoPagamento;
    
    @Column(name = "dados_pagamento", length = 500)
    private String dadosPagamento;
    
    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRecarga status = StatusRecarga.PENDENTE;
    
    @Column(name = "codigo_transacao", length = 50)
    private String codigoTransacao;
    
    @Column(name = "mensagem_erro", length = 500)
    private String mensagemErro;
    
    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;
    
    @Column(name = "data_processamento")
    private LocalDateTime dataProcessamento;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    public Recarga() {}
    
    public Recarga(Cliente cliente, String numeroCelular, BigDecimal valor, 
                   String tipoPagamento, String dadosPagamento) {
        this.cliente = cliente;
        this.numeroCelular = numeroCelular;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.dadosPagamento = dadosPagamento;
    }
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
        dataSolicitacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
        
        if (status == StatusRecarga.PROCESSANDO && dataProcessamento == null) {
            dataProcessamento = LocalDateTime.now();
        }
        
        if ((status == StatusRecarga.CONCLUIDA || status == StatusRecarga.ERRO) 
            && dataConclusao == null) {
            dataConclusao = LocalDateTime.now();
        }
    }
    
    public boolean isProcessada() {
        return status == StatusRecarga.CONCLUIDA || status == StatusRecarga.ERRO;
    }
    
    public boolean podeSerCancelada() {
        return status == StatusRecarga.PENDENTE || status == StatusRecarga.PROCESSANDO;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public String getNumeroCelular() { return numeroCelular; }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
    public MetodoPagamento getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(MetodoPagamento metodoPagamento) { this.metodoPagamento = metodoPagamento; }
    
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    
    public String getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(String dadosPagamento) { this.dadosPagamento = dadosPagamento; }
    
    public StatusRecarga getStatus() { return status; }
    public void setStatus(StatusRecarga status) { this.status = status; }
    
    public String getCodigoTransacao() { return codigoTransacao; }
    public void setCodigoTransacao(String codigoTransacao) { this.codigoTransacao = codigoTransacao; }
    
    public String getMensagemErro() { return mensagemErro; }
    public void setMensagemErro(String mensagemErro) { this.mensagemErro = mensagemErro; }
    
    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    
    public LocalDateTime getDataProcessamento() { return dataProcessamento; }
    public void setDataProcessamento(LocalDateTime dataProcessamento) { this.dataProcessamento = dataProcessamento; }
    
    public LocalDateTime getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDateTime dataConclusao) { this.dataConclusao = dataConclusao; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}