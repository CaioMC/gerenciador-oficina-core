package com.fiap.pj.infra.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PlateValidator implements ConstraintValidator<Plate, String> {

    @Override
    public boolean isValid(String plate, ConstraintValidatorContext context) {
        if (Objects.isNull(plate) || plate.isEmpty()) {
            return false;
        }
        // Exemplo de validação: placas antigas e novas seguem formatos diferentes
        // Placas antigas: 3 letras + 4 números (ex: ABC1234)
        // Placas novas: 3 letras + 1 número + 1 letra + 2 números (ex: ABC1D23)
        String regexAntiga = "^[A-Z]{3}\\d{4}$";
        String regexNova = "^[A-Z]{3}\\d[A-Z]\\d{2}$";
        return plate.matches(regexAntiga) || plate.matches(regexNova);
    }
}