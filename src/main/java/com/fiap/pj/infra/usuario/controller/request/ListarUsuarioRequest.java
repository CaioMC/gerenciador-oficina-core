package com.fiap.pj.infra.usuario.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class ListarUsuarioRequest {

    private String nome;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean ativo;


}
