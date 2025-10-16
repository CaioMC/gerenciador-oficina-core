package com.fiap.pj.infra.ordemservico.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ListarOrdemServicoRequest {

    private UUID id;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    private OrdemServicoStatus status;
    private ZonedDateTime dataCriacaoDe;
    private ZonedDateTime dataCriacaoAte;
    private ZonedDateTime dataConclusaoDe;
    private ZonedDateTime dataConclusaoAte;
    @Setter
    @JsonIgnore
    private Pageable pageable;

}