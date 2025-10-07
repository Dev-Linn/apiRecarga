package com.example.apiRecarga.controller;

import com.example.apiRecarga.dto.ClienteCreateDTO;
import com.example.apiRecarga.dto.ClienteResponseDTO;
import com.example.apiRecarga.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody ClienteCreateDTO clienteDTO) {
        try {
            ClienteResponseDTO clienteSalvo = clienteService.criarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar cliente: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        try {
            ClienteResponseDTO cliente = clienteService.buscarClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar cliente: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listarClientes() {
        try {
            List<ClienteResponseDTO> clientes = clienteService.listarClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao listar clientes: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, 
                                            @Valid @RequestBody ClienteCreateDTO clienteDTO) {
        try {
            ClienteResponseDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao atualizar cliente: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}