package com.agendamento.unhas.agendamento.api.controller;

import com.agendamento.unhas.agendamento.api.dto.AgendamentoRequestDTO;
import com.agendamento.unhas.agendamento.api.model.Agendamento;
import com.agendamento.unhas.agendamento.api.model.Bloqueio;
import com.agendamento.unhas.agendamento.api.model.Servico;
import com.agendamento.unhas.agendamento.api.model.Usuario;
import com.agendamento.unhas.agendamento.api.repository.AgendamentoRepository;
import com.agendamento.unhas.agendamento.api.repository.BloqueioRepository;
import com.agendamento.unhas.agendamento.api.repository.ServicoRepository;
import com.agendamento.unhas.agendamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired private AgendamentoRepository agendamentoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ServicoRepository servicoRepository;
    @Autowired private BloqueioRepository bloqueioRepository; // Novo!

    // Listar TUDO (Admin)
    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    // NOVO: Listar só da Cliente Específica
    @GetMapping("/cliente/{id}")
    public List<Agendamento> listarPorCliente(@PathVariable Long id) {
        return agendamentoRepository.findByClienteId(id);
    }

    // Criar Agendamento (Com verificação de Bloqueio)
    @PostMapping
    public Agendamento criarAgendamento(@RequestBody AgendamentoRequestDTO dados) {
        
        // 1. Verifica se a data está bloqueada pela Admin
        List<Bloqueio> conflitos = bloqueioRepository.encontrarConflitos(dados.dataHora);
        if (!conflitos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este horário está bloqueado pela administração.");
        }

        // 2. Buscas normais
        Usuario cliente = usuarioRepository.findById(dados.clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Servico servico = servicoRepository.findById(dados.servicoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));

        Agendamento novo = new Agendamento();
        novo.setCliente(cliente);
        novo.setServico(servico);
        novo.setDataHora(dados.dataHora);
        novo.setObservacoes(dados.observacoes);
        novo.setStatus("AGENDADO");

        return agendamentoRepository.save(novo);
    }

    // NOVO: Cancelar Agendamento (Muda status para CANCELADO)
    @PutMapping("/{id}/cancelar")
    public void cancelarAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
        
        agendamento.setStatus("CANCELADO");
        agendamentoRepository.save(agendamento);
    }

    // NOVO: Remarcar Agendamento (Muda só a Data)
    @PutMapping("/{id}/remarcar")
    public Agendamento remarcarAgendamento(@PathVariable Long id, @RequestBody AgendamentoRequestDTO dados) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
        
        // Verifica se a nova data não está bloqueada
        List<Bloqueio> conflitos = bloqueioRepository.encontrarConflitos(dados.dataHora);
        if (!conflitos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova data escolhida está bloqueada.");
        }

        agendamento.setDataHora(dados.dataHora);
        return agendamentoRepository.save(agendamento);
    }
}

//RODAR SERVIDOR: ./mvnw clean spring-boot:run -DskipTests