package com.fiap.pj.infra.orcamento.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class ListarOrcamentoRequest {

    private UUID clienteId;

    @Setter
    @JsonIgnore
    private Pageable pageable;


}
