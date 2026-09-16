package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Контракт для сервиса генерации моделей недвижимости.
 */
public interface RealtyGenerator {

    /**
     * Сгенерировать записи.
     * @param count Количество записей.
     * @return Сгенерированные модели.
     */
    Collection<RealtyDto> generate(int count);
}
