package com.fiap.pj.core.email.app.usecase.command;

import com.fiap.pj.core.email.domain.enums.Template;

import java.util.List;

public record EnviarEmailCommand(
        String destinatario,
        Template template,
        List<Object> args
) {
}