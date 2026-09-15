package com.example.Infrastructure.Services.External;

import com.example.Domain.Contracts.External.JacksonParser;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Сервис, реализующий функционал парсинга JSON файла, возвращает стрим данных.
 */
public class JacksonStreamService<T> implements JacksonParser<T> {
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, true);
    private final JsonFactory factory = mapper.getFactory();

    public Stream<T> streamRealtyJson(File jsonFile, Class<T> targetClass) throws IOException {
        JsonParser parser = factory.createParser(jsonFile);
        JsonNode rootNode = mapper.readTree(parser);

        if (!rootNode.isArray()) {
            throw new IllegalArgumentException("Ошибка валидации: Корневой элемент не массив.");
        }

        return StreamSupport.stream(rootNode.spliterator(), false).map(node -> {
            try {
                return mapper.treeToValue(node, targetClass);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка маппинга полученного объекта.");
            }
        });
    }
}
