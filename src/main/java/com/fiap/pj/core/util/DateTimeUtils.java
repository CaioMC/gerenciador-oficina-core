package com.fiap.pj.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtils {

    public static ZonedDateTime getNow() {
        return toUtc(ZonedDateTime.now());
    }

    private static ZonedDateTime toUtc(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(ZoneOffset.UTC).withNano(0);
    }

    public static boolean validarTempoMenor30Min(ZonedDateTime dataCriacao) {
        ZonedDateTime agora = DateTimeUtils.getNow();

        Duration diff = Duration.between(toUtc(dataCriacao), agora);

        return diff.toMinutes() <= 30;
    }
}