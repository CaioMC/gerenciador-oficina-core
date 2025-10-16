package com.fiap.pj.infra.orcamento.persistence.specification;

import com.fiap.pj.infra.orcamento.persistence.OrcamentoEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.UUID;


public class OrcamentoSpecification {

    private static final String FIELD_CLIENTE_ID = "clienteId";

    private UUID clienteId;

    public OrcamentoSpecification(UUID clienteId) {
        this.clienteId = clienteId;
    }


    public Specification<OrcamentoEntity> buildSpecification() {
        Specification<OrcamentoEntity> specs = Specification.allOf();

        if (Objects.nonNull(clienteId)) {
            specs = specs.and(queContenhaClienteIgualA(this.clienteId));
        }

        return specs;
    }

    private Specification<OrcamentoEntity> queContenhaClienteIgualA(UUID clienteId) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_CLIENTE_ID), clienteId);
    }
}