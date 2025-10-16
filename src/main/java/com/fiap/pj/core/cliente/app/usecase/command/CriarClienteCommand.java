package com.fiap.pj.core.cliente.app.usecase.command;

import com.fiap.pj.infra.util.validator.CPFCNPJ;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CriarClienteCommand extends ClienteCommand {

    @NotBlank(message = "Numero do Documento de identificacão não pode estar vazio.")
    @CPFCNPJ
    private String documentoIdentificacao;

    public CriarClienteCommand(String nome, String email, String telefone, String endereco, String documentoIdentificacao) {
        super(nome, email, telefone, endereco);
        this.documentoIdentificacao = documentoIdentificacao;
    }
}
