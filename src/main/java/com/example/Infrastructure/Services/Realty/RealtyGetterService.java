package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Реализация контракта {@link RealtyGetter}.
 */
public class RealtyGetterService implements RealtyGetter {

    @Override
    public Collection<RealtyDto> getRealty(Integer limit) {
        var data = Data.getRealty();

        var filteredRealty = applyFilters(data, limit);

        return filteredRealty.stream().map(r -> RealtyDto.RealtyBuilder.create()
                .setAddress(r.address)
                .setCost(r.cost)
                .setTotalArea(r.totalArea)
                .build())
            .toList();
    }

    /**
     * Применить фильтрацию.
     * @param realty Коллекция фильтруемых недвижимостей.
     * @param limit Ограничение на количество.
     * @return Фильтрованная коллекция недвижимостей.
     */
    private Collection<RealtyDao> applyFilters(Collection<RealtyDao> realty, Integer limit){
        var realtyStream = realty.stream();

        if (limit != null){
            realtyStream = realtyStream.limit(limit);
        }

        return realtyStream.toList();
    }
}
