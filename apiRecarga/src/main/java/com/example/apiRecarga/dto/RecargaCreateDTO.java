package com.example.apiRecarga.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class RecargaCreateDTO {
    
    @NotNull(message = "Cliente ID é obrigatório")
    private Long clienteId;
    
    @NotBlank(message = "Número do celular é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Número deve estar no formato (XX)XXXXX-XXXX")
    private String numeroCelular;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valor;
    
    // Dados de pagamento simplificados
    @NotBlank(message = "Tipo de pagamento é obrigatório")
    private String tipoPagamento; // CARTÃO, PIX, BOLETO
    
    @NotBlank(message = "Dados de pagamento são obrigatórios")
    private String dadosPagamento; // JSON com dados do cartão/PIX
    
    public RecargaCreateDTO() {}
    
    public RecargaCreateDTO(Long clienteId, String numeroCelular, BigDecimal valor, 
                           String tipoPagamento, String dadosPagamento) {
        this.clienteId = clienteId;
        this.numeroCelular = numeroCelular;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.dadosPagamento = dadosPagamento;
    }
    
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    
    public String getNumeroCelular() { return numeroCelular; }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    
    public String getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(String dadosPagamento) { this.dadosPagamento = dadosPagamento; }
}