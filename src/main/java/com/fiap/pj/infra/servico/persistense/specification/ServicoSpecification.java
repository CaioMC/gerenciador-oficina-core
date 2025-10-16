package com.fiap.pj.infra.servico.persistense.specification;

import com.fiap.pj.infra.servico.persistense.ServicoEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.infra.util.SpecificationUtils.likeTerm;
import static org.springframework.util.StringUtils.hasText;


public class ServicoSpecification {


    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_ATIVO = "ativo";

    private final String nome;
    private final Boolean ativo;

    public ServicoSpecification(String nome, Boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }

    public Specification<ServicoEntity> buildSpecification() {
        Specification<ServicoEntity> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (Objects.nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }

    private Specification<ServicoEntity> queContenhaNomeCom(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_DESCRICAO)), likeTerm(name.trim().toUpperCase()));
    }

    private Specification<ServicoEntity> queContenhaAtivoIgualA(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ATIVO), active);
    }
}
