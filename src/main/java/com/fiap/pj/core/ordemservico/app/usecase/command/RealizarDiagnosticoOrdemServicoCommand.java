package com.fiap.pj.core.ordemservico.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RealizarDiagnosticoOrdemServicoCommand {

    @Setter
    @JsonIgnore
    private UUID ordemServicoId;

    @NotBlank(message = "A descrição do diagnóstico deve ser preenchida.")
    private String descricao;

}