package com.agendamento.unhas.agendamento.api.repository;

import com.agendamento.unhas.agendamento.api.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    // Pronto! Já temos métodos para buscar, salvar e deletar serviços.
}