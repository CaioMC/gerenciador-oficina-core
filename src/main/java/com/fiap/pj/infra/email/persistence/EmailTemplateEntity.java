package com.fiap.pj.infra.email.persistence;

import com.fiap.pj.core.email.domain.enums.Template;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_template")
@NoArgsConstructor
@Getter
public class EmailTemplateEntity implements Serializable {

    private static final long serialVersionUID = -5209424579593010947L;

    @Id
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "template")
    Template template;

    @Column(name = "assunto")
    String assunto;

    @Column(name = "corpo", columnDefinition = "text")
    String corpo;

    @Column(name = "data_criacao")
    ZonedDateTime dataCriacao;
}