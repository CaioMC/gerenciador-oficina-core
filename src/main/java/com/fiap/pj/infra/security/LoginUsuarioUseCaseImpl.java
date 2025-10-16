package com.fiap.pj.infra.security;


import com.fiap.pj.core.usuario.app.usecase.LoginUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.infra.exception.CredenciaisInvalidasException;
import com.fiap.pj.infra.helpers.JwtUtil;
import com.fiap.pj.infra.usuario.controller.response.LoginUsuarioResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class LoginUsuarioUseCaseImpl implements LoginUsuarioUseCase {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginUsuarioResponse handle(LoginUsuarioCommand cmd) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cmd.email(), cmd.senha()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
            final String jwt = jwtUtil.generateToken(userDetailsImpl);

            return new LoginUsuarioResponse(jwt);
        } catch (AuthenticationException e) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
    }
}
