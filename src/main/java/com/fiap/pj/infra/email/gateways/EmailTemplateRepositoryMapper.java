package com.fiap.pj.infra.email.gateways;

import com.fiap.pj.core.email.domain.EmailTemplate;
import com.fiap.pj.infra.email.persistence.EmailTemplateEntity;

public class EmailTemplateRepositoryMapper {

    public EmailTemplate mapToDomain(EmailTemplateEntity entity) {
        return new EmailTemplate(
                entity.getId(),
                entity.getTemplate(),
                entity.getAssunto(),
                entity.getCorpo(),
                entity.getDataCriacao()
        );
    }
}