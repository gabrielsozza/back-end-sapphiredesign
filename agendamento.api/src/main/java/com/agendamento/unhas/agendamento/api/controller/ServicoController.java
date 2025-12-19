package com.agendamento.unhas.agendamento.api.controller;

import com.agendamento.unhas.agendamento.api.model.Servico;
import com.agendamento.unhas.agendamento.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos") // Rota: localhost:8080/servicos
@CrossOrigin(origins = "*") // Libera para o HTML acessar
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    // Listar todos os serviços
    @GetMapping
    public List<Servico> listarServicos() {
        return repository.findAll();
    }

    // Cadastrar novo serviço (Útil para a tela da Admin depois)
    @PostMapping
    public Servico criarServico(@RequestBody Servico servico) {
        return repository.save(servico);
    }
}