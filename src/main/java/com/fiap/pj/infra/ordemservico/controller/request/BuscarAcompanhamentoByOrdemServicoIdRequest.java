package com.fiap.pj.infra.ordemservico.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class BuscarAcompanhamentoByOrdemServicoIdRequest {

    private UUID id;

}


