package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;

import java.util.Collection;
import java.util.Comparator;

/**
 * Сервис сортировки недвижимости.
 */
public interface RealtySorter {

    /**
     * Отсортировать коллекцию недвижимости.
     *
     * @param realty Коллекция недвижимости для сортировки.
     * @param comparator Компаратор, определяющий порядок сортировки.
     * @return Отсортированная коллекция недвижимости.
     */
    Collection<RealtyDto> sort(
            Collection<RealtyDto> realty,
            Comparator<RealtyDto> comparator
    );
}
