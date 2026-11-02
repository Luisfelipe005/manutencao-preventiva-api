package com.luisfelipe.manutencao.controller;

import com.luisfelipe.manutencao.model.Manutencao;
import com.luisfelipe.manutencao.service.ManutencaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {
    private final ManutencaoService manutencaoService;


    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @PostMapping
    public Manutencao cadastrarManutencao(@Valid @RequestBody Manutencao manutencao){
       return manutencaoService.cadastrar(manutencao);
    }

    @GetMapping
    public List<Manutencao> listarManutencoes(){
        return manutencaoService.listarManutencoes();
    }

    @GetMapping("/{id}")
    public Manutencao buscaPorId(@PathVariable Long id){
        return  manutencaoService.buscaPorId(id);
    }

    @PutMapping("/{id}")
    public Manutencao atualizaManutencao(@Valid @RequestBody Manutencao manutencao,@PathVariable Long id){
        return manutencaoService.atualizarManutencao(manutencao, id);
    }

    @DeleteMapping("/{id}")
    public void deleteManutencao(@PathVariable Long id){
        manutencaoService.delete(id);
    }


}
