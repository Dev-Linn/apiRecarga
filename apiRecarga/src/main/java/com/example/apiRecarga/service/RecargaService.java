package com.example.apiRecarga.service;

import com.example.apiRecarga.dto.RecargaCreateDTO;
import com.example.apiRecarga.dto.RecargaResponseDTO;
import com.example.apiRecarga.model.*;
import com.example.apiRecarga.repository.*;
import com.example.apiRecarga.messaging.RecargaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecargaService {
    
    @Autowired
    private RecargaRepository recargaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    
    @Autowired
    private RecargaMessageProducer recargaMessageProducer;
    
    public RecargaResponseDTO criarRecarga(RecargaCreateDTO recargaDTO) {
        //valida o cliente
        Cliente cliente = clienteRepository.findById(recargaDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + recargaDTO.getClienteId()));
        
        Recarga recarga = new Recarga(
            cliente,
            recargaDTO.getNumeroCelular(),
            recargaDTO.getValor(),
            recargaDTO.getTipoPagamento(),
            recargaDTO.getDadosPagamento()
        );
        
        recarga.setCodigoTransacao(UUID.randomUUID().toString());
        
        Recarga recargaSalva = recargaRepository.save(recarga);
        
        //envia a recarga para o processamento assinc
        recargaMessageProducer.enviarRecargaParaProcessamento(recargaSalva.getId());
        
        return converterParaResponseDTO(recargaSalva);
    }
    
    public RecargaResponseDTO buscarRecargaPorId(Long id) {
        Recarga recarga = recargaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recarga não encontrada com ID: " + id));
        
        return converterParaResponseDTO(recarga);
    }
    
    public Page<RecargaResponseDTO> listarRecargasPorCliente(Long clienteId, Pageable pageable) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + clienteId);
        }
        
        Page<Recarga> recargas = recargaRepository.findByClienteId(clienteId, pageable);
        return recargas.map(this::converterParaResponseDTO);
    }
    
    public List<RecargaResponseDTO> listarRecargasPorClienteSimples(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + clienteId);
        }
        
        List<Recarga> recargas = recargaRepository.findByClienteId(clienteId);
        return recargas.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<RecargaResponseDTO> listarRecargasSimples() {
        List<Recarga> recargas = recargaRepository.findAll();
        return recargas.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Page<RecargaResponseDTO> listarRecargas(Pageable pageable, StatusRecarga status, Long operadoraId) {
        Page<Recarga> recargas;
        
        if (status != null) {
            recargas = recargaRepository.findByStatus(status, pageable);
        } else {
            recargas = recargaRepository.findAll(pageable);
        }
        
        return recargas.map(this::converterParaResponseDTO);
    }
    
    
    public void processarRecarga(Long recargaId) {
        //busca a recarga
        Recarga recarga = recargaRepository.findById(recargaId)
            .orElseThrow(() -> new RuntimeException("Recarga não encontrada com ID: " + recargaId));
        
        recarga.setStatus(StatusRecarga.PROCESSANDO);
        recargaRepository.save(recarga);
        
        try {
            Thread.sleep(2000 + (long)(Math.random() * 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        boolean sucesso = Math.random() < 0.9;
        
        if (sucesso) {
            recarga.setStatus(StatusRecarga.CONCLUIDA);
            recarga.setMensagemErro(null);
        } else {
            recarga.setStatus(StatusRecarga.ERRO);
            recarga.setMensagemErro("Falha na comunicação com a operadora. Tente novamente.");
        }
        
        recargaRepository.save(recarga);
    }
    
    
    private RecargaResponseDTO converterParaResponseDTO(Recarga recarga) {
        return new RecargaResponseDTO(
            recarga.getId(),
            recarga.getCliente().getId(),
            recarga.getCliente().getNome(),
            recarga.getNumeroCelular(),
            recarga.getValor(),
            recarga.getTipoPagamento(),
            recarga.getStatus(),
            recarga.getCodigoTransacao(),
            recarga.getMensagemErro(),
            recarga.getDataSolicitacao(),
            recarga.getDataProcessamento(),
            recarga.getDataConclusao(),
            recarga.getDataCriacao(),
            recarga.getDataAtualizacao()
        );
    }
    
}
