package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;

import java.nio.file.Path;

/**
 * Контракт загрузки данных из файла.
 */
public interface RealtyImporter {
    /**
     * Загрузить данные из файла.
     * @param filePath абсолютный путь к файлу.
     * @return true - если загрузка прошла успешно, иначе false.
     */
    Result<Boolean> importFromFile(Path filePath);
}
