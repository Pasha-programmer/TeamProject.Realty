package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;

/**
 * Контракт загрузки данных о недвижимости из JSON файла.
 */
public interface RealtyUploader {
    /**
     * Загрузить данные о недвижимости из файла.
     * @param filePath абсолютный путь к файлу.
     * @return true - если загрузка прошла успешно, иначе false.
     */
    Result<Boolean> uploadRealty(String filePath);
}
