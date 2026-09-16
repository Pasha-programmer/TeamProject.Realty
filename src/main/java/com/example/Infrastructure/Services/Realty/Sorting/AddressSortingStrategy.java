package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Sorting.ComparatorStrategy;
import com.example.Domain.Models.RealtyDto;

import java.util.Comparator;

/**
 * Стратегия сортировки недвижимости по адресу.
 */
public class AddressSortingStrategy implements ComparatorStrategy<RealtyDto> {

    @Override
    public Comparator<RealtyDto> getComparator() {
        return Comparator.comparing(RealtyDto::getAddress);
    }
}
