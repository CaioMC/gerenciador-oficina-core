package com.fiap.pj.infra.cliente.persistence.specification;

import com.fiap.pj.infra.cliente.persistence.ClienteEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.infra.util.SpecificationUtils.likeTerm;
import static org.springframework.util.StringUtils.hasText;


public class ClienteSpecification {

    private static final String TABLE_VEICULO = "veiculos";
    private static final String FIELD_NOME = "nome";
    private static final String FIELD_ATIVO = "ativo";
    private static final String FIELD_DOCUMENTO_IDENTIFICACAO = "documentoIdentificacao";
    private static final String FIELD_DOCUMENTO_IDENTIFICACAO_NUMERO = "numero";
    private static final String FIELD_PLACA = "placa";

    private final String nome;
    private final String documentoIdentificacao;
    private final String placa;
    private final Boolean ativo;

    public ClienteSpecification(String nome, String documentoIdentificacao, String placa, Boolean ativo) {
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.placa = placa;
        this.ativo = ativo;
    }

    public Specification<ClienteEntity> buildSpecification() {
        Specification<ClienteEntity> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (hasText(this.documentoIdentificacao)) {
            specs = specs.and(queContenhaDocumentoIdentificacaoCom(this.documentoIdentificacao));
        }

        if (hasText(this.placa)) {
            specs = specs.and(queContenhaPlacaIgualA(this.placa));
        }

        if (Objects.nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }


    private Specification<ClienteEntity> queContenhaNomeCom(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NOME)), likeTerm(name.trim().toUpperCase()));
    }

    private Specification<ClienteEntity> queContenhaAtivoIgualA(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ATIVO), active);
    }

    private Specification<ClienteEntity> queContenhaDocumentoIdentificacaoCom(String identificationDocument) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_DOCUMENTO_IDENTIFICACAO).get(FIELD_DOCUMENTO_IDENTIFICACAO_NUMERO), identificationDocument.trim().toUpperCase());
    }

    private Specification<ClienteEntity> queContenhaPlacaIgualA(String plate) {
        return (root, criteriaQuery, builder) ->
        {
            var veiculo = root.join(TABLE_VEICULO);
            return builder.like(veiculo.get(FIELD_PLACA), likeTerm(plate.trim().toUpperCase()));
        };
    }
}
