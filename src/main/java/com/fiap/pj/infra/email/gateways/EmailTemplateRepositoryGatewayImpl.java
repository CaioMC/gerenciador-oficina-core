package com.fiap.pj.infra.email.gateways;

import com.fiap.pj.core.email.app.gateways.EmailGateway;
import com.fiap.pj.core.email.domain.EmailTemplate;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.infra.email.persistence.EmailTemplateRepositoryJpa;

import java.util.Optional;

public class EmailTemplateRepositoryGatewayImpl implements EmailGateway {

    private final EmailTemplateRepositoryJpa repository;
    private final EmailTemplateRepositoryMapper emailTemplateRepositoryMapper;

    public EmailTemplateRepositoryGatewayImpl(EmailTemplateRepositoryJpa repository, EmailTemplateRepositoryMapper emailTemplateRepositoryMapper) {
        this.repository = repository;
        this.emailTemplateRepositoryMapper = emailTemplateRepositoryMapper;
    }

    @Override
    public Optional<EmailTemplate> buscarTemplate(Template template) {
        return this.repository.findByTemplate(template)
                .map(emailTemplateRepositoryMapper::mapToDomain);
    }
}