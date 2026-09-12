package com.example.Domain.Contracts.Sorting;

import java.util.Comparator;

/**
 * Стратегия сортировки данных.
 * @param <T> Тип сортируемых данных.
 */
public interface SortingStrategy<T> {

    /**
     * Получить компаратор для сортировки данных.
     * @return Компаратор для сравнения элементов.
     */
    Comparator<T> getComparator();
}
