package com.agendamento.unhas.agendamento.api.repository;

import com.agendamento.unhas.agendamento.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    // Método extra para buscar agendamentos de um cliente específico (útil para o histórico)
    List<Agendamento> findByClienteId(Long clienteId);
}