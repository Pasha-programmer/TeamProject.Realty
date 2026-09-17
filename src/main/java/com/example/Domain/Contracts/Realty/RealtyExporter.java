package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Models.RealtyDto;

import java.nio.file.Path;
import java.util.Collection;

/**
 * Контракт экспорта данных в файл.
 */
public interface RealtyExporter {
    /**
     * Сохранить данные текущей коллекции в файл.
     * @param data Данные для экспорта.
     * @param filePath абсолютный путь к json файлу.
     * @return true - если сохранение прошло успешно, иначе false.
     */
    Result<Boolean> saveToFile(Collection<RealtyDto> data, Path filePath);

}
