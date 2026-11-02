package com.luisfelipe.manutencao.service;

import com.luisfelipe.manutencao.dto.EquipamentoCriticidadeDTO;
import com.luisfelipe.manutencao.exception.ClienteNaoEncontradoException;
import com.luisfelipe.manutencao.exception.EquipamentoNaoEncontradoException;
import com.luisfelipe.manutencao.exception.ManutencaoNaoEncontrada;
import com.luisfelipe.manutencao.model.*;
import com.luisfelipe.manutencao.repository.ClienteRepository;
import com.luisfelipe.manutencao.repository.EquipamentoRepository;
import com.luisfelipe.manutencao.repository.ManutencaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ManutencaoRepository manutencaoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, ClienteRepository clienteRepository, ManutencaoRepository manutencaoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.clienteRepository = clienteRepository;
        this.manutencaoRepository = manutencaoRepository;
    }

    public int quantidadeDeFalhas(Long id){
        List<Manutencao> list = manutencaoRepository.findByEquipamentoId(id);
        int contParaTrue = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isFalha()){
                contParaTrue++;
            }
        }
        return contParaTrue;
    }

    public int pontuacaoFalhas(Long id){
        int contPontuacao = 0;
        int cont = quantidadeDeFalhas(id);
        if (cont == 0){
            return contPontuacao;
        } else if (cont == 1 || cont == 2) {
            contPontuacao = 1;
            return contPontuacao;
        }
        contPontuacao = 2;
        return contPontuacao;
    }

    public int quantidadeDeCorretivas(Long id){
        List<Manutencao> list = manutencaoRepository.findByEquipamentoId(id);
        int contCorretiva = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTipo() == TipoManutencao.CORRETIVA){
                contCorretiva++;
            }
        }
        return contCorretiva;
    }

    public int pontuacaoCorretivas(Long id){
        int pontos = 0;
        int cont = quantidadeDeCorretivas(id);
        if (cont == 0){
            return pontos;
        } else if (cont == 1 || cont == 2) {
            pontos = 1;
            return pontos;
        }
        pontos = 2;
        return pontos;
    }

    public int tempoDeUso(Long id){
        Equipamento equipamento = buscaPorId(id);
        LocalDate hoje = LocalDate.now();
        Period period = Period.between(equipamento.getDataInstalacao(),hoje);
        return period.getYears();
    }

    public int pontuacaoDeTempoDeUso(Long id){
        int pontos = 0;
        int contYears = tempoDeUso(id);
        if (contYears <= 2){
            return pontos;
        } else if (contYears <= 5) {
            pontos = 1;
            return pontos;
        }
        pontos = 2;
        return pontos;
    }

    public LocalDate dataUltimaManutencao(Long id){
        List<Manutencao> list = manutencaoRepository.findByEquipamentoId(id);
        if(list.isEmpty()){
            throw new ManutencaoNaoEncontrada("Manutenção não encontrada!");
        }
        LocalDate ultimaManutencao = list.getFirst().getDataManutencao();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDataManutencao().isAfter(ultimaManutencao)){
                ultimaManutencao = list.get(i).getDataManutencao();
            }
        }

        return ultimaManutencao;
    }

    public LocalDate dataProximaManutencao(Long id){
        Equipamento equipamento = buscaPorId(id);
        LocalDate ultimaManutencao = dataUltimaManutencao(id);
        int periodicidadeManutencao = equipamento.getPeriodicidadeManutencao();
         return ultimaManutencao.plusMonths(periodicidadeManutencao);

    }

    public int pontuacaoManutencaoPreventiva(Long id){
        LocalDate hoje = LocalDate.now();
        LocalDate proxManutencao = dataProximaManutencao(id);
        int pontos = 0;
        if (hoje.isBefore(proxManutencao) || hoje.isEqual(proxManutencao)){
            return pontos;
        }

        long diasAtraso = ChronoUnit.DAYS.between(proxManutencao, hoje);

        if (diasAtraso <= 30){
            pontos = 1;
            return pontos;
        }
        pontos = 2;
        return pontos;
    }

    public NivelCriticidade classificacaoCriticidade(Long id){
        int pontos = 0;
        pontos += pontuacaoFalhas(id);
        pontos += pontuacaoCorretivas(id);
        pontos += pontuacaoDeTempoDeUso(id);
        pontos += pontuacaoManutencaoPreventiva(id);

        if (pontos <= 2){
            return NivelCriticidade.BAIXA;
        } else if (pontos <= 5) {
            return NivelCriticidade.MEDIA;
        }
        return NivelCriticidade.ALTA;
    }

    public List<EquipamentoCriticidadeDTO> listarEquipamentosComCriticidade (){
        List<EquipamentoCriticidadeDTO> equipamentoCriticidadeDTOList = new ArrayList<>();
        List<Equipamento> equipamentos = equipamentoRepository.findAll();

        for (int i = 0; i < equipamentos.size(); i++) {
            long id = equipamentos.get(i).getId();
            String modelo = equipamentos.get(i).getModelo();
            String marca = equipamentos.get(i).getMarca();
            NivelCriticidade nivelCriticidade = classificacaoCriticidade(equipamentos.get(i).getId());
            EquipamentoCriticidadeDTO aux = new EquipamentoCriticidadeDTO(id, modelo, marca, nivelCriticidade);
            equipamentoCriticidadeDTOList.add(aux);
        }
        return equipamentoCriticidadeDTOList;
    }

    public Equipamento cadastrar(Equipamento equipamento){
        Cliente cliente = clienteRepository.findById(equipamento.getCliente().getId()).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        equipamento.setCliente(cliente);
        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listaEquipamentos(){
        return equipamentoRepository.findAll();
    }

    public Equipamento buscaPorId(Long id){
        return equipamentoRepository.findById(id).orElseThrow(() -> new EquipamentoNaoEncontradoException("Equipamento não encontrado"));
    }

    public Equipamento atualizar(Equipamento equipamento, Long id){
        Equipamento equipamentoAux = equipamentoRepository.findById(id).orElseThrow();
        Cliente cliente = clienteRepository.findById(equipamento.getCliente().getId()).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        equipamentoAux.setCliente(cliente);
        equipamentoAux.setDataInstalacao(equipamento.getDataInstalacao());
        equipamentoAux.setMarca(equipamento.getMarca());
        equipamentoAux.setModelo(equipamento.getModelo());
        equipamentoAux.setPeriodicidadeManutencao(equipamento.getPeriodicidadeManutencao());

        return equipamentoRepository.save(equipamentoAux);
    }

    public void delete(Long id){
        equipamentoRepository.deleteById(id);
    }

}
