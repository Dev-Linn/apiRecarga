package com.example.apiRecarga.controller;

import com.example.apiRecarga.dto.RecargaCreateDTO;
import com.example.apiRecarga.dto.RecargaResponseDTO;
import com.example.apiRecarga.service.RecargaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recargas")
public class RecargaController {
    
    @Autowired
    private RecargaService recargaService;
    
    @PostMapping
    public ResponseEntity<?> criarRecarga(@Valid @RequestBody RecargaCreateDTO recargaDTO) {
        try {
            RecargaResponseDTO recargaSalva = recargaService.criarRecarga(recargaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(recargaSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar recarga: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRecarga(@PathVariable Long id) {
        try {
            RecargaResponseDTO recarga = recargaService.buscarRecargaPorId(id);
            return ResponseEntity.ok(recarga);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar recarga: " + e.getMessage());
        }
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> listarRecargasPorCliente(@PathVariable Long clienteId) {
        try {
            List<RecargaResponseDTO> recargas = recargaService.listarRecargasPorClienteSimples(clienteId);
            return ResponseEntity.ok(recargas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao listar recargas do cliente: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listarRecargas() {
        try {
            List<RecargaResponseDTO> recargas = recargaService.listarRecargasSimples();
            return ResponseEntity.ok(recargas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao listar recargas: " + e.getMessage());
        }
    }
}