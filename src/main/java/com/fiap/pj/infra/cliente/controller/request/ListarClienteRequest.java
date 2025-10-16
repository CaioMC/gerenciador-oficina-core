package com.fiap.pj.infra.cliente.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class ListarClienteRequest {

    @Setter
    @JsonIgnore
    private Pageable pageable;
    private String nome;
    private String documentoIdentificacao;
    private String placa;
    private Boolean ativo;
}
