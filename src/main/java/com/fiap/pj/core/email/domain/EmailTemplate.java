package com.fiap.pj.core.email.domain;

import com.fiap.pj.core.email.domain.enums.Template;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class EmailTemplate implements Serializable {

    private static final long serialVersionUID = -5209424579593010947L;

    @Id
    UUID id;

    @Column(nullable = false)
    Template template;

    @Column(nullable = false)
    String assunto;

    @Column(nullable = false)
    String corpo;

    @Column(nullable = false)
    ZonedDateTime dataCriacao;

    public EmailTemplate(UUID id, Template template, String assunto, String corpo, ZonedDateTime dataCriacao) {
        this.id = id;
        this.template = template;
        this.assunto = assunto;
        this.corpo = corpo;
        this.dataCriacao = dataCriacao;
    }

    public String getHtml(Object... args) {
        return String.format(this.corpo, args);
    }
}