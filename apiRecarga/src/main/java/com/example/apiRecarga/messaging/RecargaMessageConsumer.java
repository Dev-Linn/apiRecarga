package com.example.apiRecarga.messaging;

import com.example.apiRecarga.service.RecargaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecargaMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(RecargaMessageConsumer.class);
    
    @Autowired
    private RecargaService recargaService;
    
    @RabbitListener(queues = "recarga.queue")
    public void processarRecarga(Long recargaId) {
        try {
            recargaService.processarRecarga(recargaId);
        } catch (Exception e) {
            logger.error("Erro ao processar recarga ID {}: {}", recargaId, e.getMessage());
            
        }
    }
}
