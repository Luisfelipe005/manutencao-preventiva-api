package com.luisfelipe.manutencao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @NotNull
    private LocalDate dataInstalacao;

    @NotNull
    private Integer periodicidadeManutencao;

    @ManyToOne
    @NotNull
    private Cliente cliente;

    public Equipamento() {
    }

    public Equipamento(String modelo, String marca, LocalDate dataInstalacao, Integer periodicidadeManutencao, Cliente cliente) {
        this.modelo = modelo;
        this.marca = marca;
        this.dataInstalacao = dataInstalacao;
        this.periodicidadeManutencao = periodicidadeManutencao;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(LocalDate dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public Integer getPeriodicidadeManutencao() {
        return periodicidadeManutencao;
    }

    public void setPeriodicidadeManutencao(Integer periodicidadeManutencao) {
        this.periodicidadeManutencao = periodicidadeManutencao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
