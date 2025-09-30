package com.example.apiRecarga.repository;

import com.example.apiRecarga.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long> {
    
    List<MetodoPagamento> findByClienteIdAndAtivoTrueOrderByPadraoDescDataCriacaoDesc(Long clienteId);
    
    List<MetodoPagamento> findByClienteIdOrderByPadraoDescDataCriacaoDesc(Long clienteId);
    
    Optional<MetodoPagamento> findByClienteIdAndPadraoTrueAndAtivoTrue(Long clienteId);
    
    Optional<MetodoPagamento> findByIdAndClienteId(Long id, Long clienteId);
}
