package com.luisfelipe.manutencao.service;

import com.luisfelipe.manutencao.exception.EquipamentoNaoEncontradoException;
import com.luisfelipe.manutencao.exception.ManutencaoNaoEncontrada;
import com.luisfelipe.manutencao.model.Equipamento;
import com.luisfelipe.manutencao.model.Manutencao;
import com.luisfelipe.manutencao.repository.EquipamentoRepository;
import com.luisfelipe.manutencao.repository.ManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManutencaoService {
    private final ManutencaoRepository manutencaoRepository;
    private final EquipamentoRepository equipamentoRepository;


    public ManutencaoService(ManutencaoRepository manutencaoRepository, EquipamentoRepository equipamentoRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Manutencao cadastrar(Manutencao manutencao){
        Equipamento equipamento = equipamentoRepository.findById(manutencao.getEquipamento().getId()).orElseThrow(() -> new EquipamentoNaoEncontradoException("Equipamento não foi encontrado!"));
        manutencao.setEquipamento(equipamento);
        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> listarManutencoes(){
        return manutencaoRepository.findAll();
    }

    public Manutencao buscaPorId(Long id){
       return manutencaoRepository.findById(id).orElseThrow(() -> new ManutencaoNaoEncontrada("Manutenção não encontrada! "));

    }

    public Manutencao atualizarManutencao(Manutencao manutencao, Long id){
        Equipamento equipamento = equipamentoRepository.findById(manutencao.getEquipamento().getId()).orElseThrow(() -> new EquipamentoNaoEncontradoException("Equipamento não foi encontrado!"));
        Manutencao manutencaoAux = manutencaoRepository.findById(id).orElseThrow(() -> new ManutencaoNaoEncontrada("Manutenção não encontrada!"));
        manutencaoAux.setEquipamento(equipamento);
        manutencaoAux.setDataManutencao(manutencao.getDataManutencao());
        manutencaoAux.setDescricao(manutencao.getDescricao());
        manutencaoAux.setFalha(manutencao.isFalha());
        manutencaoAux.setTipo(manutencao.getTipo());
        return manutencaoRepository.save(manutencaoAux);
    }

    public void delete(Long id){
        buscaPorId(id);
        manutencaoRepository.deleteById(id);
    }
}
