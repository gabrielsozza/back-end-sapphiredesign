package com.agendamento.unhas.agendamento.api.repository;

import com.agendamento.unhas.agendamento.api.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    
    // Verifica se existe algum bloqueio batendo com o horário que a cliente quer
    @Query("SELECT b FROM Bloqueio b WHERE " +
           "(b.inicio <= :dataHora AND b.fim >= :dataHora)")
    List<Bloqueio> encontrarConflitos(LocalDateTime dataHora);
}