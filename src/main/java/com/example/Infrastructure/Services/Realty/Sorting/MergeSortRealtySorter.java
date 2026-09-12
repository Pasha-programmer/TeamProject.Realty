package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Models.RealtyDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис сортировки недвижимости с использованием алгоритма Merge Sort.
 */
public class MergeSortRealtySorter implements RealtySorter {
    @Override
    public Collection<RealtyDto> sort(
            Collection<RealtyDto> realty,
            Comparator<RealtyDto> comparator
    ) {
        List<RealtyDto> list = new ArrayList<>(realty);

        if (list.size() <= 1) {
            return list;
        }

        int middle = list.size() / 2;

        List<RealtyDto> left = new ArrayList<>(list.subList(0, middle));
        List<RealtyDto> right = new ArrayList<>(list.subList(middle, list.size()));

        List<RealtyDto> sortedLeft = new ArrayList<>(sort(left, comparator));
        List<RealtyDto> sortedRight = new ArrayList<>(sort(right, comparator));

        return merge(sortedLeft, sortedRight, comparator);
    }

    /**
     * Объединить два отсортированных списка недвижимости.
     *
     * @param left Левый отсортированный список.
     * @param right Правый отсортированный список.
     * @param comparator Компаратор, определяющий порядок сортировки.
     * @return Объединённый отсортированный список.
     */
    private List<RealtyDto> merge(
            List<RealtyDto> left,
            List<RealtyDto> right,
            Comparator<RealtyDto> comparator
    ) {
        List<RealtyDto> result = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            RealtyDto leftElement = left.get(leftIndex);
            RealtyDto rightElement = right.get(rightIndex);

            if (comparator.compare(leftElement, rightElement) <= 0) {
                result.add(leftElement);
                leftIndex++;
            } else {
                result.add(rightElement);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        return result;
    }
}
