package com.example.Domain.Contracts.External;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Контракт парсинга данных из JSON файла.
 */
public interface JsonParser<T> {
    /**
     * Распарсить данные о требуемой сущности из файла и вернуть стрим сущностей.
     * @param jsonFile файл для парсинга.
     * @param targetClass класс для маппинга полей в ожидаемую сущность <T>
     * @return Stream<T> - если парсинг прошел успешно, иначе выбросит исключение.
     */
    Stream<T> streamTargets(File jsonFile, Class<T> targetClass) throws IOException;
}
