package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Contracts.Realty.RealtySortingStrategy;
import com.example.Domain.Contracts.Sorting.ComparatorStrategy;
import com.example.Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Стратегия сортировки недвижимости с использованием компаратора.
 */
public class ComparatorRealtySortingStrategy implements RealtySortingStrategy {

    private final RealtySorter realtySorter;
    private final ComparatorStrategy<RealtyDto> comparatorStrategy;

    public ComparatorRealtySortingStrategy(
            RealtySorter realtySorter,
            ComparatorStrategy<RealtyDto> comparatorStrategy
    ) {
        this.realtySorter = realtySorter;
        this.comparatorStrategy = comparatorStrategy;
    }

    @Override
    public Collection<RealtyDto> sort(Collection<RealtyDto> realty) {
        return realtySorter.sort(
                realty,
                comparatorStrategy.getComparator()
        );
    }
}
