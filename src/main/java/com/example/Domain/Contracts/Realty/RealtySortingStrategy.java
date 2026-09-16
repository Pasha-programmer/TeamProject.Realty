package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Стратегия сортировки недвижимости.
 */
public interface RealtySortingStrategy {

    /**
     * Отсортировать коллекцию недвижимости.
     *
     * @param realty Коллекция недвижимости для сортировки.
     * @return Результат сортировки.
     */
    Collection<RealtyDto> sort(Collection<RealtyDto> realty);
}
