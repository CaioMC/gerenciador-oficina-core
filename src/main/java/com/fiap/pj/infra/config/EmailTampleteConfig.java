package com.fiap.pj.infra.config;

import com.fiap.pj.core.email.app.EnviarEmailUseCaseImpl;
import com.fiap.pj.core.email.app.gateways.EmailGateway;
import com.fiap.pj.infra.email.gateways.EmailTemplateRepositoryGatewayImpl;
import com.fiap.pj.infra.email.gateways.EmailTemplateRepositoryMapper;
import com.fiap.pj.infra.email.persistence.EmailTemplateRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailTampleteConfig {

    @Bean
    EnviarEmailUseCaseImpl enviarEmailUseCase(EmailGateway emailGateway, JavaMailSender mailSender) {
        return new EnviarEmailUseCaseImpl(emailGateway, mailSender);
    }

    @Bean
    EmailTemplateRepositoryGatewayImpl emailTemplateGateway(EmailTemplateRepositoryJpa repository, EmailTemplateRepositoryMapper emailTemplateRepositoryMapper) {
        return new EmailTemplateRepositoryGatewayImpl(repository, emailTemplateRepositoryMapper);
    }

    @Bean
    EmailTemplateRepositoryMapper emailTemplateMapper() {
        return new EmailTemplateRepositoryMapper();
    }
}