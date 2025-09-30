package com.example.apiRecarga.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MetodoPagamentoCreateDTO {
    
    @NotNull(message = "Cliente ID é obrigatório")
    private Long clienteId;
    
    @NotBlank(message = "Tipo de pagamento é obrigatório")
    private String tipoPagamento;
    
    @NotBlank(message = "Descrição é obrigatório")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String descricao;
    
    @NotNull(message = "Dados do pagamento são obrigatórios")
    @Size(max = 1000, message = "Dados devem ter no máximo 1000 caracteres")
    private String dadosPagamento;
    
    private Boolean padrao = false;
    
    public MetodoPagamentoCreateDTO() {}
    
    public MetodoPagamentoCreateDTO(Long clienteId, String tipoPagamento, String descricao, 
                                   String dadosPagamento, Boolean padrao) {
        this.clienteId = clienteId;
        this.tipoPagamento = tipoPagamento;
        this.descricao = descricao;
        this.dadosPagamento = dadosPagamento;
        this.padrao = padrao;
    }
    
    // Getters e Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(String dadosPagamento) { this.dadosPagamento = dadosPagamento; }
    
    public Boolean getPadrao() { return padrao; }
    public void setPadrao(Boolean padrao) { this.padrao = padrao; }
}
