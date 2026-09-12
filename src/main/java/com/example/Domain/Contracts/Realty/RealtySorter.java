package com.example.Domain.Contracts.Realty;

import com.example.Domain.Models.RealtyDto;

import java.util.Collection;
import java.util.Comparator;

public interface RealtySorter {
    Collection<RealtyDto> sort(
            Collection<RealtyDto> realty,
            Comparator<RealtyDto> comparator
    );
}
