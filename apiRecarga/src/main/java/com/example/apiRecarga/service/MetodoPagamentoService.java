package com.example.apiRecarga.service;

import com.example.apiRecarga.dto.MetodoPagamentoCreateDTO;
import com.example.apiRecarga.dto.MetodoPagamentoResponseDTO;
import com.example.apiRecarga.model.Cliente;
import com.example.apiRecarga.model.MetodoPagamento;
import com.example.apiRecarga.repository.ClienteRepository;
import com.example.apiRecarga.repository.MetodoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MetodoPagamentoService {
    
    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public MetodoPagamentoResponseDTO criarMetodoPagamento(MetodoPagamentoCreateDTO metodoPagamentoDTO) {
        Cliente cliente = clienteRepository.findById(metodoPagamentoDTO.getClienteId())
            .orElseThrow(() -> 

new RuntimeException("Cliente não encontrado com ID: " + metodoPagamentoDTO.getClienteId()));
        
        //Se for o metodo padrao, tira o padrao setado anteriormente
        if (Boolean.TRUE.equals(metodoPagamentoDTO.getPadrao())) {
            Optional<MetodoPagamento> metodoAtualPadrao = metodoPagamentoRepository
                .findByClienteIdAndPadraoTrueAndAtivoTrue(cliente.getId());
            //remove o padrao setado antes
            if (metodoAtualPadrao.isPresent()) {
                MetodoPagamento metodo = metodoAtualPadrao.get();
                metodo.setPadrao(false);
                metodoPagamentoRepository.save(metodo);
            }
        }
        
        MetodoPagamento metodoPagamento = new MetodoPagamento(
            cliente,
            metodoPagamentoDTO.getTipoPagamento(),
            metodoPagamentoDTO.getDescricao(),
            metodoPagamentoDTO.getDadosPagamento()
        );
        //verifica se colocou o metodo padrao
        metodoPagamento.setPadrao(metodoPagamentoDTO.getPadrao() != null ? metodoPagamentoDTO.getPadrao() : false);
        
        MetodoPagamento metodoSalvo = metodoPagamentoRepository.save(metodoPagamento);
        
        return new MetodoPagamentoResponseDTO(metodoSalvo);
    }
    
    public MetodoPagamentoResponseDTO buscarMetodoPagamentoPorId(Long id, Long clienteId) {
        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findByIdAndClienteId(id, clienteId)
            .orElseThrow(() -> new RuntimeException("metodo de pagamento nao encontrado com ID: " + id));
        
        return new MetodoPagamentoResponseDTO(metodoPagamento);
    }
    
    public List<MetodoPagamentoResponseDTO> listarMetodosPagamentoPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("cliente nao encontrado com ID: " + clienteId);
        }
        
        List<MetodoPagamento> metodos = metodoPagamentoRepository.findByClienteIdAndAtivoTrueOrderByPadraoDescDataCriacaoDesc(clienteId);
        
        return metodos.stream()
                .map(MetodoPagamentoResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    public MetodoPagamentoResponseDTO atualizarMetodoPagamento(Long id, MetodoPagamentoCreateDTO metodoPagamentoDTO) {
        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findByIdAndClienteId(id, metodoPagamentoDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("metodo de pagamento não encontrado com o id: " + id));
        
        if (Boolean.TRUE.equals(metodoPagamentoDTO.getPadrao())) {
            Optional<MetodoPagamento> atualPadrao = metodoPagamentoRepository
                .findByClienteIdAndPadraoTrueAndAtivoTrue(metodoPagamentoDTO.getClienteId());
            
            if (atualPadrao.isPresent() && !atualPadrao.get().getId().equals(id)) {
                MetodoPagamento metodo = atualPadrao.get();
                metodo.setPadrao(false);
                metodoPagamentoRepository.save(metodo);
            }
        }
        
        metodoPagamento.setTipoPagamento(metodoPagamentoDTO.getTipoPagamento());
        metodoPagamento.setDescricao(metodoPagamentoDTO.getDescricao());
        metodoPagamento.setDadosPagamento(metodoPagamentoDTO.getDadosPagamento());
        metodoPagamento.setPadrao(metodoPagamentoDTO.getPadrao() != null ? metodoPagamentoDTO.getPadrao() : false);
        
        MetodoPagamento metodoAtualizado = metodoPagamentoRepository.save(metodoPagamento);
        
        return new MetodoPagamentoResponseDTO(metodoAtualizado);
    }
    
    public void desativarMetodoPagamento(Long id, Long clienteId) {
        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findByIdAndClienteId(id, clienteId)
            .orElseThrow(() -> new RuntimeException("metodo de pagamento não encontrado com o id: " + id));
        
        metodoPagamento.setAtivo(false);
        metodoPagamentoRepository.save(metodoPagamento);
    }
    
    public MetodoPagamentoResponseDTO definirComoPadrao(Long id, Long clienteId) {
        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findByIdAndClienteId(id, clienteId)
            .orElseThrow(() -> new RuntimeException("metodo de pagamento não encontrado com o id: " + id));
        
        // Remove padrão de outros métodos
        Optional<MetodoPagamento> atualpadrao = metodoPagamentoRepository
            .findByClienteIdAndPadraoTrueAndAtivoTrue(clienteId);
        
        if (atualpadrao.isPresent()) {
            MetodoPagamento metodo = atualpadrao.get();
            metodo.setPadrao(false);
            metodoPagamentoRepository.save(metodo);
        }
        
        // Define o novo padrão
        metodoPagamento.setPadrao(true);
        MetodoPagamento metodoSalvo = metodoPagamentoRepository.save(metodoPagamento);
        
        return new MetodoPagamentoResponseDTO(metodoSalvo);
    }
}
