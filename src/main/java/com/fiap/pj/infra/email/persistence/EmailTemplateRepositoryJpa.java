package com.fiap.pj.infra.email.persistence;

import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface EmailTemplateRepositoryJpa extends Repository<EmailTemplateEntity, UUID>, ExtendedRepository<EmailTemplateEntity, UUID>, JpaSpecificationExecutor<EmailTemplateEntity> {


    Optional<EmailTemplateEntity> findByTemplate(Template template);

}