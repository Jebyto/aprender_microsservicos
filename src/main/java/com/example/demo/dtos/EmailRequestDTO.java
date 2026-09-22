package com.example.demo.dtos;

//DTO
public record EmailRequestDTO(String to, String subject, String body) {
    // Nenhum construtor, getter ou setter são necessários,
    // pois o 'record' gera automaticamente esses métodos imutáveis.
}
