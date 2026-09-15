package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Models.RealtyDto;

import java.util.*;

/**
 * Сервис сортировки недвижимости с использованием алгоритма Merge Sort.
 */
public class MergeSortRealtySorter implements RealtySorter {
    @Override
    public Collection<RealtyDto> sort(
            Collection<RealtyDto> realty,
            Comparator<RealtyDto> comparator
    ) {
        Objects.requireNonNull(comparator);

        List<RealtyDto> list = new ArrayList<>(realty);
        List<RealtyDto> buffer = new ArrayList<>(list);

        mergeSort(list, buffer, 0, list.size(), comparator);

        return list;
    }

    /**
     * Отсортировать диапазон списка недвижимости методом Merge Sort.
     *
     * @param list Список недвижимости.
     * @param buffer Временный список для объединения данных.
     * @param from Начало сортируемого диапазона.
     * @param to Конец сортируемого диапазона.
     * @param comparator Компаратор, определяющий порядок сортировки.
     */
    private void mergeSort(
            List<RealtyDto> list,
            List<RealtyDto> buffer,
            int from,
            int to,
            Comparator<RealtyDto> comparator
    ) {
        if (to - from <= 1) {
            return;
        }

        int middle = (from + to) / 2;

        mergeSort(list, buffer, from, middle, comparator);
        mergeSort(list, buffer, middle, to, comparator);

        merge(list, buffer, from, middle, to, comparator);
    }

    /**
     * Объединить два отсортированных диапазона недвижимости.
     *
     * @param list Список недвижимости.
     * @param buffer Временный список для объединения данных.
     * @param from Начало сортируемого диапазона.
     * @param middle Граница между левым и правым диапазонами.
     * @param to Конец сортируемого диапазона.
     * @param comparator Компаратор, определяющий порядок сортировки.
     */
    private void merge(
            List<RealtyDto> list,
            List<RealtyDto> buffer,
            int from,
            int middle,
            int to,
            Comparator<RealtyDto> comparator
    ) {
        int leftIndex = from;
        int rightIndex = middle;
        int bufferIndex = from;

        while (leftIndex < middle && rightIndex < to) {
            RealtyDto leftElement = list.get(leftIndex);
            RealtyDto rightElement = list.get(rightIndex);

            if (comparator.compare(leftElement, rightElement) <= 0) {
                buffer.set(bufferIndex, leftElement);
                leftIndex++;
            } else {
                buffer.set(bufferIndex, rightElement);
                rightIndex++;
            }

            bufferIndex++;
        }

        while (leftIndex < middle) {
            buffer.set(bufferIndex, list.get(leftIndex));
            leftIndex++;
            bufferIndex++;
        }

        while (rightIndex < to) {
            buffer.set(bufferIndex, list.get(rightIndex));
            rightIndex++;
            bufferIndex++;
        }

        for (int i = from; i < to; i++) {
            list.set(i, buffer.get(i));
        }
    }
}