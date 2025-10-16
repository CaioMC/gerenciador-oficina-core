package com.fiap.pj.infra.util.security;

import com.fiap.pj.infra.security.UserDetailsImpl;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityContextUtils {

    private SecurityContextUtils() {
    }

    public static AbstractAuthenticationToken getAuthentication() {
        return (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    public static UUID getUsuarioId() {
        if (getAuthentication().getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl user = (UserDetailsImpl) getAuthentication().getPrincipal();
            return user.getId();
        }

        return null;
    }
}
