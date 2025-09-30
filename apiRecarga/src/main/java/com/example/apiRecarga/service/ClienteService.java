package com.example.apiRecarga.service;

import com.example.apiRecarga.dto.ClienteCreateDTO;
import com.example.apiRecarga.dto.ClienteResponseDTO;
import com.example.apiRecarga.model.Cliente;
import com.example.apiRecarga.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    //valida com o repository, se oemail e o cpf já existem
    public ClienteResponseDTO criarCliente(ClienteCreateDTO clienteDTO) {
        if (clienteRepository.existsByEmailAndIdNot(clienteDTO.getEmail(), null)) {
            throw new RuntimeException("email ja cadastrado: " + clienteDTO.getEmail());
        }
        
        if (clienteRepository.existsByCpfAndIdNot(clienteDTO.getCpf(), null)) {
            throw new RuntimeException("cpf já cadastrado: " + clienteDTO.getCpf());
        }
        
        //cria o cliente, fznd a conversão do DTO 
        Cliente cliente = new Cliente(
            clienteDTO.getNome(),
            clienteDTO.getEmail(),
            clienteDTO.getTelefone(),
            clienteDTO.getCpf()
        );
        
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }
    
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        
        return new ClienteResponseDTO(cliente);
    }
    
    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
            .map(ClienteResponseDTO::new)
            .collect(Collectors.toList());
    }
    
    
    public ClienteResponseDTO atualizarCliente(Long id, ClienteCreateDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        

        if (clienteRepository.existsByEmailAndIdNot(clienteDTO.getEmail(), id)) {
            throw new RuntimeException("email ja cadastrado: " + clienteDTO.getEmail());
        }

        if (clienteRepository.existsByCpfAndIdNot(clienteDTO.getCpf(), id)) {
            throw new RuntimeException("CPF ja cadastrado: " + clienteDTO.getCpf());
        }
        
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCpf(clienteDTO.getCpf());
        
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteAtualizado);
    }
    
    public void desativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
    
    public boolean clienteExiste(Long id) {
        return clienteRepository.existsById(id);
    }
}
