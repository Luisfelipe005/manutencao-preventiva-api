package com.luisfelipe.manutencao.repository;

import com.luisfelipe.manutencao.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    public List<Manutencao> findByEquipamentoId(Long id);
}
