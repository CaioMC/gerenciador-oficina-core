package com.fiap.pj.core.email.app.gateways;

import com.fiap.pj.core.email.domain.EmailTemplate;
import com.fiap.pj.core.email.domain.enums.Template;

import java.util.Optional;

public interface EmailGateway {

    Optional<EmailTemplate> buscarTemplate(Template template);
}