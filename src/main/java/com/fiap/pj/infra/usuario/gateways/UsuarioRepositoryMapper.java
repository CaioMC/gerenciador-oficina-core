package com.fiap.pj.infra.usuario.gateways;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioRepositoryMapper {

    UsuarioEntity mapToTable(Usuario usuario, PasswordEncoder passwordEncoder) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobreNome(),
                usuario.getEmail(),
                passwordEncoder.encode(usuario.getSenha()),
                usuario.isAtivo(), usuario.getPerfis()

        );
    }

    Usuario mapToDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNome(),
                entity.getSobreNome(),
                entity.getEmail(),
                entity.getSenha(),
                entity.isAtivo()
        );
    }
}
