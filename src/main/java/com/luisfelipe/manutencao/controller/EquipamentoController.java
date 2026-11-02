package com.luisfelipe.manutencao.controller;

import com.luisfelipe.manutencao.dto.EquipamentoCriticidadeDTO;
import com.luisfelipe.manutencao.model.Equipamento;
import com.luisfelipe.manutencao.model.NivelCriticidade;
import com.luisfelipe.manutencao.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public Equipamento cadastrar(@Valid @RequestBody Equipamento equipamento){
        return equipamentoService.cadastrar(equipamento);
    }

    @GetMapping
    public List<Equipamento> buscarEquipamentos(){
        return equipamentoService.listaEquipamentos();
    }

    @GetMapping("/criticidade")
    public List<EquipamentoCriticidadeDTO> listarEquipamentoComCriticidade(){
        return equipamentoService.listarEquipamentosComCriticidade();
    }


    @GetMapping("/{id}")
    public Equipamento buscaEquipamentoPorId(@PathVariable Long id){
        return equipamentoService.buscaPorId(id);
    }

    @GetMapping("/{id}/criticidade")
    public NivelCriticidade classicacaoEquipamento(@PathVariable Long id){
    return equipamentoService.classificacaoCriticidade(id);
    }

    @PutMapping("/{id}")
    public Equipamento atualizaEquipamento(@PathVariable Long id, @Valid @RequestBody Equipamento equipamento){
        return equipamentoService.atualizar(equipamento, id);
    }

    @DeleteMapping("/{id}")
    public void deletePorId(@PathVariable Long id){
        equipamentoService.delete(id);
    }

}
