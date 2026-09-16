package com.example.Domain.Contracts.Sorting;

import java.util.Comparator;

/**
 * Стратегия сравнения данных для сортировки.
 *
 * @param <T> Тип сравниваемых данных.
 */
public interface ComparatorStrategy<T> {

    /**
     * Получить компаратор для сравнения данных.
     *
     * @return Компаратор для сравнения элементов.
     */
    Comparator<T> getComparator();
}
