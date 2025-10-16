package com.fiap.pj.infra.usuario.persistence.specification;

import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.infra.util.SpecificationUtils.likeTerm;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;


@Getter
public class UsuarioSpecification {

    private static final String FIELD_NOME = "nome";
    private static final String FIELD_ATIVO = "ativo";

    private String nome;
    private Boolean ativo;

    public UsuarioSpecification(String nome, Boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }

    public Specification<UsuarioEntity> buildSpecification() {
        Specification<UsuarioEntity> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }

    private Specification<UsuarioEntity> queContenhaNomeCom(String nome) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NOME)), likeTerm(nome.trim().toUpperCase()));
    }

    private Specification<UsuarioEntity> queContenhaAtivoIgualA(boolean ativo) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ATIVO), ativo);
    }


}
