package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;

import java.util.Comparator;

public interface RealtySortingStrategy {

    Comparator<RealtyDto> getComparator();
}
