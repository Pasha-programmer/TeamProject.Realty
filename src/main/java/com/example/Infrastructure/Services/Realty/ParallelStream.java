package com.example.Infrastructure.Services.Realty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public final class ParallelStream {

    /**
     * Порог, ниже которого задача решается последовательно.
     */
    private static final int THRESHOLD = 1_000;

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


    public static <T> long countIf(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null || collection.isEmpty() || predicate == null) {
            return 0L;
        }

        var list = (collection instanceof List)
                ? (List<T>) collection
                : new ArrayList<>(collection);

        return ForkJoinPool.commonPool()
                .invoke(new CountIfTask<>(list, 0, list.size(), predicate));
    }

    private static final class CountIfTask<T> extends RecursiveTask<Long> {

        private final List<T> data;
        private final int lo, hi;
        private final Predicate<T> predicate;

        CountIfTask(List<T> data, int lo, int hi, Predicate<T> predicate) {
            this.data = data;
            this.lo = lo;
            this.hi = hi;
            this.predicate = predicate;
        }

        @Override
        protected Long compute() {
            System.out.println("compute [" + lo + ", " + hi + ") in "
                    + Thread.currentThread().getName());

            var size = hi - lo;

            if (size <= THRESHOLD) {
                long count = 0L;
                for (int i = lo; i < hi; i++) {
                    if (predicate.test(data.get(i))) {
                        count++;
                    }
                }
                return count;
            }

            var mid = lo + size / 2;
            CountIfTask<T> left  = new CountIfTask<>(data, lo, mid, predicate);
            CountIfTask<T> right = new CountIfTask<>(data, mid, hi, predicate);

            left.fork();
            var rightResult = right.compute();
            var leftResult  = left.join();

            return leftResult + rightResult;
        }
    }
}
