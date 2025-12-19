package com.agendamento.unhas.agendamento.api.dto;

import java.time.LocalDateTime;

// Essa classe serve APENAS para receber os dados do Front-end
public class AgendamentoRequestDTO {
    public Long clienteId;
    public Long servicoId;
    public LocalDateTime dataHora;
    public String observacoes;
}