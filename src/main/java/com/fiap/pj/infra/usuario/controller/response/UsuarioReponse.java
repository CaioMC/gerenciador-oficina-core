package com.fiap.pj.infra.usuario.controller.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "nome", "sobreNome", "email", "ativo"})
public interface UsuarioReponse {

    String getId();

    String getNome();

    String getSobreNome();

    String getEmail();

    boolean isAtivo();
}
