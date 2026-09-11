package com.example.Infrastructure.Services.Realty;

import java.util.Collection;

public final class ParallelStream {
    /**
     * Получить количество элементов равных искомому.
     * @param collection Колллекция поиска.
     * @param targetElement Искомый элемент.
     * @return Количество элементов.
     * @param <T> Тип элемента
     * @implNote Для сравнения используется метод equals в типе данных {@link T}
     */
    public static <T> long getCountElement(Collection<T> collection, T targetElement) {
        if (collection == null || collection.isEmpty() || targetElement == null) {
            return 0;
        }

        // Используем параллельный стрим для подсчета
        return collection.parallelStream()
            .filter(element -> element.equals(targetElement))
            .count();
    }
}
