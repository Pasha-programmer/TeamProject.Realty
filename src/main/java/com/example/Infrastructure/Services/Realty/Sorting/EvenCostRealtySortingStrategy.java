package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Contracts.Realty.RealtySortingStrategy;
import com.example.Domain.Models.RealtyDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки недвижимости с чётной целой частью стоимости.
 * Объекты с нечётной целой частью стоимости остаются
 * на исходных позициях, а объекты с чётной целой частью
 * стоимости сортируются по возрастанию стоимости.
 */
public class EvenCostRealtySortingStrategy implements RealtySortingStrategy {

    private final RealtySorter realtySorter;

    public EvenCostRealtySortingStrategy(RealtySorter realtySorter) {
        this.realtySorter = realtySorter;
    }

    @Override
    public List<RealtyDto> sort(Collection<RealtyDto> realty) {
        List<RealtyDto> evenRealty = new ArrayList<>();
        for (var item : realty) {
            if (isEvenCost(item)) {
                evenRealty.add(item);
            }
        }
        var sortedEvenRealty = realtySorter.sort(evenRealty, Comparator.comparing(RealtyDto::getCost));

        List<RealtyDto> result = new ArrayList<>(realty);

        var sortedIterator = sortedEvenRealty.iterator();

        for (int i = 0; i < result.size(); i++) {
            if (isEvenCost(result.get(i))) {
                result.set(i, sortedIterator.next());
            }
        }

        return result;
    }

    private boolean isEvenCost(RealtyDto realty) {
        return realty
                .getCost()
                .toBigInteger()
                .remainder(BigInteger.TWO)
                .equals(BigInteger.ZERO);
    }
}
