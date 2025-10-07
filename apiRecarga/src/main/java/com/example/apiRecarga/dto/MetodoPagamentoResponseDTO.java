package com.example.apiRecarga.dto;

import com.example.apiRecarga.model.MetodoPagamento;
import java.time.LocalDateTime;

public class MetodoPagamentoResponseDTO {
    
    private Long id;
    private Long clienteId;
    private String clienteNome;
    private String tipoPagamento;
    private String descricao;
    private String dadosPagamento;
    private Boolean padrao;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    public MetodoPagamentoResponseDTO() {}
    
    public MetodoPagamentoResponseDTO(Long id, Long clienteId, String clienteNome, String tipoPagamento,
                                   String descricao, String dadosPagamento, Boolean padrao, Boolean ativo,
                                     LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.tipoPagamento = tipoPagamento;
        this.descricao = descricao;
        this.dadosPagamento = dadosPagamento;
        this.padrao = padrao;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
    
    public MetodoPagamentoResponseDTO(MetodoPagamento metodoPagamento) {
        this.id = metodoPagamento.getId();
        this.clienteId = metodoPagamento.getCliente().getId();
        this.clienteNome = metodoPagamento.getCliente().getNome();
        this.tipoPagamento = metodoPagamento.getTipoPagamento();
        this.descricao = metodoPagamento.getDescricao();
        this.dadosPagamento = metodoPagamento.getDadosPagamento();
        this.padrao = metodoPagamento.getPadrao();
        this.ativo = metodoPagamento.getAtivo();
        this.dataCriacao = metodoPagamento.getDataCriacao();
        this.dataAtualizacao = metodoPagamento.getDataAtualizacao();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    
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
