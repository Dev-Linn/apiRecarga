package com.example.apiRecarga.repository;

import com.example.apiRecarga.model.Recarga;
import com.example.apiRecarga.model.StatusRecarga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga, Long> {
    
    List<Recarga> findByClienteId(Long clienteId);
    
    Page<Recarga> findByClienteId(Long clienteId, Pageable pageable);
    
    Optional<Recarga> findByCodigoTransacao(String codigoTransacao);
    
    List<Recarga> findByStatus(StatusRecarga status);
    
    List<Recarga> findByNumeroCelular(String numeroCelular);
    
    @Query("SELECT COUNT(r) FROM Recarga r WHERE r.status = :status")
    Long countByStatus(@Param("status") StatusRecarga status);
    
    @Query("SELECT COUNT(r) FROM Recarga r WHERE r.cliente.id = :clienteId")
    Long countByClienteId(@Param("clienteId") Long clienteId);
    
    Page<Recarga> findByStatus(StatusRecarga status, Pageable pageable);
}