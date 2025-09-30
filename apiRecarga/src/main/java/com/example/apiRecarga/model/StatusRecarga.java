package com.example.apiRecarga.model;


public enum StatusRecarga {
    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    CONCLUIDA("Concluída"),
    ERRO("Erro"),
    CANCELADA("Cancelada");
    
    private final String descricao;
    
    StatusRecarga(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}

