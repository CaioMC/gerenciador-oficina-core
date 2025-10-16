package com.fiap.pj.core.email.app.usecase;

import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;

public interface EnviarEmailUseCase {

    void handle(EnviarEmailCommand cmd);
}
