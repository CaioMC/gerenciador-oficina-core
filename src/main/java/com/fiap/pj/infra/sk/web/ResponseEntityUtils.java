package com.fiap.pj.infra.sk.web;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.UUID;

@UtilityClass
public final class ResponseEntityUtils {

    public static ResponseEntity<ResponseId> create(Class<?> classe, UUID id) {
        var uri = MvcUriComponentsBuilder.
                fromController(classe).path("/{id}").buildAndExpand(id.toString()).toUri();
        return ResponseEntity.created(uri).body(new ResponseId(id));
    }

    public static ResponseEntity<ResponseId> ok(UUID id) {
        return ResponseEntity.ok(new ResponseId(id));
    }

    public record ResponseId(UUID id) {
    }

}
