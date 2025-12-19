package com.agendamento.unhas.agendamento.api.repository;

import com.agendamento.unhas.agendamento.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
    // Podemos criar métodos personalizados também, ex:
    Optional<Usuario> findByEmail(String email);
}