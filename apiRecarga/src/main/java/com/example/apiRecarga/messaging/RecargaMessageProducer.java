package com.example.apiRecarga.messaging;

import com.example.apiRecarga.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecargaMessageProducer {
    
    private static final Logger logger = LoggerFactory.getLogger(RecargaMessageProducer.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void enviarRecargaParaProcessamento(Long recargaId) {
        try {

            rabbitTemplate.convertAndSend(
                RabbitMQConfig.RECARGA_EXCHANGE,
                RabbitMQConfig.RECARGA_ROUTING_KEY,
                recargaId
            );
            

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar recarga para processamento", e);
        }
    }
}
