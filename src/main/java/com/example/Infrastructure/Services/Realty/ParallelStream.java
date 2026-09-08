package com.example.Infrastructure.Services.Realty;

import java.util.Collection;
import java.util.Comparator;

public final class ParallelStream {
    public static <T> long getCountElement(Collection<T> collection, T targetElement, Comparator<T> comparator) {
        if (collection == null || collection.isEmpty() || targetElement == null) {
            return 0;
        }

        // Используем параллельный стрим для подсчета
        return collection.parallelStream()
            .filter(element -> comparator.compare(element, targetElement) == 0)
            .count();
    }
}
