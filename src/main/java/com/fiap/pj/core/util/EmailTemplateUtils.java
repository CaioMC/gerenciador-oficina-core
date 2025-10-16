package com.fiap.pj.core.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;

public class EmailTemplateUtils {
    private EmailTemplateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String readEmailTemplateAsString(String templateName) {
        try (InputStream in = getFileFromResourceAsStream(templateName);
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader bufferedWriter = new BufferedReader(reader)) {
            return bufferedWriter.lines().collect(joining("\n"));
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }

    private static InputStream getFileFromResourceAsStream(String templateName) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("email" + separator + templateName);
    }
}
