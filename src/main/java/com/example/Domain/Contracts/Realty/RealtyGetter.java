package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Models.RealtyFilterParametersDto;

import java.util.Collection;

/**
 * Контракт получения информации о недвижимости.
 */
public interface RealtyGetter {

    /**
     * Получить коллекцию полной информации о недвижимости.
     * @param limit Ограничение количества получения моделей.
     * @return Коллекция моделей информации о недвижимости.
     */
    public Collection<RealtyDto> getRealty(Integer limit);

    /**
     * Получить количество записей о недвижимости.
     * @param filterParameters Параметры фильтрации.
     * @return Количество записей.
     */
    public Integer getRealtyCount(RealtyFilterParametersDto filterParameters);

    /**
     * Получить количество записей о недвижимости, равных искомому объекту.
     * @param targetRealty Искомый объект недвижимости.
     * @return Количество записей.
     */
    public Integer getRealtyCount(RealtyDto targetRealty);
}
