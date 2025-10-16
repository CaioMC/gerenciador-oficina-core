package com.fiap.pj.infra.cliente.controller.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;

import java.util.List;

@JsonPropertyOrder({"id", "nome", "email", "telefone", "endereco", "ativo"})
public interface ClienteResponse {

    String getId();

    String getNome();

    String getEmail();

    String getTelefone();

    String getEndereco();

    DocumentoIdentificacao getDocumentoIdentificacao();

    @JsonIgnoreProperties({"clienteId"})
    List<VeiculoEntity> getVeiculos();

    boolean isAtivo();
}
