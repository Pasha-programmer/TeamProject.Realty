package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;

import java.nio.file.Path;

/**
 * Контракт сохранения данных в файл.
 */
public interface RealtyUploader {
    /**
     * Сохранить данные текущей коллекции в файл.
     * @param filePath абсолютный путь к файлу.
     * @return true - если сохранение прошло успешно, иначе false.
     */
    Result<Boolean> saveToFile(Path filePath);

}
