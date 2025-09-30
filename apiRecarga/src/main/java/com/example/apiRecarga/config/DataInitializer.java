package com.example.apiRecarga.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Sistema de recarga inicializado com sucesso!");
    }
}