package com.agendamento.unhas.agendamento.api.controller;
import com.agendamento.unhas.agendamento.api.model.Bloqueio;
import com.agendamento.unhas.agendamento.api.repository.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bloqueios") // <--- É ISSO QUE CRIA O LINK
@CrossOrigin(origins = "*")
public class BloqueioController {
    @Autowired private BloqueioRepository repository;

    @GetMapping
    public List<Bloqueio> listar() { return repository.findAll(); }

    @PostMapping
    public Bloqueio criar(@RequestBody Bloqueio bloqueio) {
        return repository.save(bloqueio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { repository.deleteById(id); }
}