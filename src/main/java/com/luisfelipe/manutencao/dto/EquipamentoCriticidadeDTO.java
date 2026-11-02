package com.luisfelipe.manutencao.dto;

import com.luisfelipe.manutencao.model.NivelCriticidade;

public class EquipamentoCriticidadeDTO {
    private Long id;
    private String modelo;
    private String marca;
    private NivelCriticidade criticidade;

    public EquipamentoCriticidadeDTO(Long id, String modelo, String marca, NivelCriticidade criticidade) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.criticidade = criticidade;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public NivelCriticidade getCriticidade() {
        return criticidade;
    }
}
