package com.fiap.pj.core.veiculo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
public class Veiculo {

    private UUID id;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private UUID clienteId;

    @Builder
    public Veiculo(UUID id, String placa, String modelo, String marca, int ano, UUID clienteId) {
        this.id = requireNonNull(id);
        this.placa = requireNonNull(placa);
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.clienteId = requireNonNull(clienteId);
    }
}
