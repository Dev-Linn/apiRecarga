package com.example.apiRecarga.dto;

import com.example.apiRecarga.model.StatusRecarga;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecargaResponseDTO {
    
    private Long id;
    private Long clienteId;
    private String clienteNome;
    private String numeroCelular;
    private BigDecimal valor;
    private String tipoPagamento;
    private StatusRecarga status;
    private String codigoTransacao;
    private String mensagemErro;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataProcessamento;
    private LocalDateTime dataConclusao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    public RecargaResponseDTO() {}
    
    public RecargaResponseDTO(Long id, Long clienteId, String clienteNome, String numeroCelular, 
                             BigDecimal valor, String tipoPagamento, StatusRecarga status, 
                             String codigoTransacao, String mensagemErro, LocalDateTime dataSolicitacao,
                             LocalDateTime dataProcessamento, LocalDateTime dataConclusao,
                             LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.numeroCelular = numeroCelular;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.status = status;
        this.codigoTransacao = codigoTransacao;
        this.mensagemErro = mensagemErro;
        this.dataSolicitacao = dataSolicitacao;
        this.dataProcessamento = dataProcessamento;
        this.dataConclusao = dataConclusao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    
    public String getNumeroCelular() { return numeroCelular; }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    
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