package com.agendamento.unhas.agendamento.api.controller;

import com.agendamento.unhas.agendamento.api.model.Usuario;
import com.agendamento.unhas.agendamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // O endereço será: localhost:8080/usuarios
@CrossOrigin(origins = "*") // Libera o acesso para o Front-end (HTML)
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Rota para listar todos os usuários (GET)
    @GetMapping
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Rota para criar um novo usuário (POST)
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
}