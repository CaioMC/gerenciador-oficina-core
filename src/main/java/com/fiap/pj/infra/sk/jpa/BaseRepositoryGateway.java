package com.fiap.pj.infra.sk.jpa;

import java.util.Optional;
import java.util.UUID;

public interface BaseRepositoryGateway<T> {

    T salvar(T domain);

    void alterar(T domain);

    void excluir(T domain);

    Optional<T> buscarPorId(UUID id);
}
