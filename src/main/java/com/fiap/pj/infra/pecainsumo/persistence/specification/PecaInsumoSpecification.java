package com.fiap.pj.infra.pecainsumo.persistence.specification;

import com.fiap.pj.infra.pecainsumo.persistence.PecaInsumoEntity;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.util.StringUtils.hasText;


public class PecaInsumoSpecification {

    private static final String FIELD_MODELO_VEICULO = "modeloVeiculo";
    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_QUANTIDADE_ESTOQUE = "quantidadeEstoque";
    private static final String FIELD_QUANTIDADE_MIN_ESTOQUE = "quantidadeMinimoEstoque";

    private final String modeloVeiculo;
    private final String descricao;
    private final Boolean estoqueBaixo;

    public PecaInsumoSpecification(String modeloVeiculo, String descricao, Boolean estoqueBaixo) {
        this.modeloVeiculo = modeloVeiculo;
        this.descricao = descricao;
        this.estoqueBaixo = estoqueBaixo;
    }

    public Specification<PecaInsumoEntity> buildSpecification() {
        Specification<PecaInsumoEntity> specs = Specification.allOf();

        if (hasText(this.modeloVeiculo)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(FIELD_MODELO_VEICULO)), "%" + this.modeloVeiculo.toLowerCase() + "%"));
        }

        if (hasText(this.descricao)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(FIELD_DESCRICAO)), "%" + this.descricao.toLowerCase() + "%"));
        }

        if (this.estoqueBaixo != null && this.estoqueBaixo) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_QUANTIDADE_ESTOQUE), root.get(FIELD_QUANTIDADE_MIN_ESTOQUE)));
        }

        return specs;
    }
}
