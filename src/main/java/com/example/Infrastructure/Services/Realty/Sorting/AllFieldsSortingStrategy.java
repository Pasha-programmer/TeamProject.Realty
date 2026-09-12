package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySortingStrategy;
import com.example.Domain.Models.RealtyDto;

import java.util.Comparator;

public class AllFieldsSortingStrategy implements RealtySortingStrategy {

    @Override
    public Comparator<RealtyDto> getComparator() {
        return Comparator
                .comparing(RealtyDto::getAddress)
                .thenComparing(RealtyDto::getTotalArea)
                .thenComparing(RealtyDto::getCost);
    }
}
