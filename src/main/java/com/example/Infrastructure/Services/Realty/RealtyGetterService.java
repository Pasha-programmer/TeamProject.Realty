package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.Comparators.RealtyComparator;
import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Models.RealtyFilterParametersDto;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Реализация контракта {@link RealtyGetter}.
 */
public class RealtyGetterService implements RealtyGetter {

    @Override
    public Collection<RealtyDto> getRealty(Integer limit) {
        var data = Data.getRealty();

        var filteredRealty = applyFilters(data, null, limit);

        return filteredRealty.stream()
            .map(this::mapRealtyDaoToDto)
            .toList();
    }

    @Override
    public Integer getRealtyCount(RealtyFilterParametersDto filterParameters) {
        var data = Data.getRealty();

        var filteredRealty = applyFilters(data, filterParameters, null);

        return filteredRealty.size();
    }

    @Override
    public Integer getRealtyCount(RealtyDto targetRealty) {
        var data = Data.getRealty();

        var realtyDtos = data.stream()
                .map(this::mapRealtyDaoToDto)
                .toList();

        return (int)ParallelStream.getCountElement(realtyDtos, targetRealty);
    }

    /**
     * Применить фильтрацию.
     * @param realty Коллекция фильтруемых недвижимостей.
     * @param limit Ограничение на количество.
     * @return Фильтрованная коллекция недвижимостей.
     */
    private Collection<RealtyDao> applyFilters(Collection<RealtyDao> realty, RealtyFilterParametersDto filterParameters, Integer limit){
        var realtyStream = realty.stream();

        realtyStream = applyFilters(realtyStream, filterParameters);

        if (limit != null){
            realtyStream = realtyStream.limit(limit);
        }

        return realtyStream.toList();
    }

    /**
     * Применить фильтрацию.
     * @param realtyStream Стрим записей о недвижимости.
     * @param filterParameters Параметры фильтрации.
     * @return Стрим записей с примененной фильтрацией.
     */
    private Stream<RealtyDao> applyFilters(Stream<RealtyDao> realtyStream, RealtyFilterParametersDto filterParameters){
        if (filterParameters == null){
            return realtyStream;
        }

        if (filterParameters.address != null && !filterParameters.address.isEmpty()){
            realtyStream = realtyStream.filter(r -> r.address.contains(filterParameters.address));
        }

        if (filterParameters.fromCost != null){
            realtyStream = realtyStream.filter(r -> r.cost.compareTo(filterParameters.fromCost) >= 0);
        }

        if (filterParameters.toCost != null){
            realtyStream = realtyStream.filter(r -> r.cost.compareTo(filterParameters.toCost) <= 0);
        }

        if (filterParameters.fromTotalArea != null){
            realtyStream = realtyStream.filter(r -> r.totalArea >= filterParameters.fromTotalArea);
        }

        if (filterParameters.toTotalArea != null){
            realtyStream = realtyStream.filter(r -> r.totalArea <= filterParameters.toTotalArea);
        }

        return realtyStream;
    }

    /**
     * Преобразовать DAO в DTO для объекта недвижимости.
     * @param realtyDao DAO недвижимости.
     * @return DTO недвижимости.
     */
    private RealtyDto mapRealtyDaoToDto(RealtyDao realtyDao){
        return RealtyDto.RealtyBuilder.create()
            .setAddress(realtyDao.address)
            .setCost(realtyDao.cost)
            .setTotalArea(realtyDao.totalArea)
            .build();
    }
}
