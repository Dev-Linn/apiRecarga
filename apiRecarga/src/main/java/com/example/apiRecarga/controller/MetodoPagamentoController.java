package com.example.apiRecarga.controller;

import com.example.apiRecarga.dto.MetodoPagamentoCreateDTO;
import com.example.apiRecarga.dto.MetodoPagamentoResponseDTO;
import com.example.apiRecarga.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodos-pagamento")
public class MetodoPagamentoController {

    @Autowired
    private MetodoPagamentoService metodoPagamentoService;
    
    @PostMapping
    public ResponseEntity<?> criarMetodoPagamento(@Valid @RequestBody MetodoPagamentoCreateDTO metodoPagamentoDTO) {
        try {
            MetodoPagamentoResponseDTO metodoSalvo = metodoPagamentoService.criarMetodoPagamento(metodoPagamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(metodoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar método de pagamento: " + e.getMessage());
        }
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> listarMetodosPagamentoPorCliente(@PathVariable Long clienteId) {
        try {
            List<MetodoPagamentoResponseDTO> metodos = metodoPagamentoService.listarMetodosPagamentoPorCliente(clienteId);
            return ResponseEntity.ok(metodos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao listar métodos de pagamento: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}/cliente/{clienteId}")
    public ResponseEntity<?> buscarMetodoPagamento(@PathVariable Long id, @PathVariable Long clienteId) {
        try {
            MetodoPagamentoResponseDTO metodo = metodoPagamentoService.buscarMetodoPagamentoPorId(id, clienteId);
            return ResponseEntity.ok(metodo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar método de pagamento: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMetodoPagamento(@PathVariable Long id, 
                                                    @Valid @RequestBody MetodoPagamentoCreateDTO metodoPagamentoDTO) {
        try {
            MetodoPagamentoResponseDTO metodoAtualizado = metodoPagamentoService.atualizarMetodoPagamento(id, metodoPagamentoDTO);
            return ResponseEntity.ok(metodoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao atualizar método de pagamento: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}/cliente/{clienteId}")
    public ResponseEntity<?> desativarMetodoPagamento(@PathVariable Long id, @PathVariable Long clienteId) {
        try {
            metodoPagamentoService.desativarMetodoPagamento(id, clienteId);
            return ResponseEntity.ok("Método de pagamento desativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao desativar método de pagamento: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}/cliente/{clienteId}/definir-padrao")
    public ResponseEntity<?> definirComoPadrao(@PathVariable Long id, @PathVariable Long clienteId) {
        try {
            MetodoPagamentoResponseDTO metodo = metodoPagamentoService.definirComoPadrao(id, clienteId);
            return ResponseEntity.ok(metodo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao definir método como padrão: " + e.getMessage());
        }
    }
}
