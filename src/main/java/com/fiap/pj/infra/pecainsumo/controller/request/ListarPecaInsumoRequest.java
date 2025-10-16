package com.fiap.pj.infra.pecainsumo.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class ListarPecaInsumoRequest {

    private String modeloVeiculo;
    private String descricao;
    private Boolean estoqueBaixo;
    
    @Setter
    @JsonIgnore
    private Pageable pageable;


} 