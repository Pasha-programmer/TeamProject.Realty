package com.example.Infrastructure.Services.Realty;

import java.lang.management.BufferPoolMXBean;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Models.RealtyFilterParametersDto;

/**
 * Реализация контракта {@link RealtyGetter}.
 */
public class RealtyGetterService implements RealtyGetter {

    @Override
    public Collection<RealtyDto> getRealty(Integer limit) {
        var data = Data.getRealty();

        var realtyStream = data.stream();

        var filteredRealty = applyFilters(realtyStream, null, limit);

        return filteredRealty
            .map(this::mapRealtyDaoToDto)
            .toList();
    }

    @Override
    public Integer getRealtyCount(RealtyFilterParametersDto filterParameters) {
        var data = Data.getRealty();

        Predicate<RealtyDao> predicate = r -> allowFilters(r, filterParameters);

        return (int) ParallelStream.countIf(data, predicate);
    }

    @Override
    public Integer getRealtyCount(RealtyDto targetRealty) {
        var data = Data.getRealty();

        var realtyDtos = data.stream()
                .map(this::mapRealtyDaoToDto)
                .toList();

        return (int) ParallelStream.getCountElement(realtyDtos, targetRealty);
    }

    /**
     * Применить фильтрацию.
     * @param realtyStream Стрим фильтруемых недвижимостей.
     * @param limit Ограничение на количество.
     * @return Фильтрованный стрим недвижимостей.
     */
    private Stream<RealtyDao> applyFilters(Stream<RealtyDao> realtyStream, RealtyFilterParametersDto filterParameters, Integer limit){
        realtyStream = applyFilters(realtyStream, filterParameters);

        if (limit != null){
            realtyStream = realtyStream.unordered().limit(limit);
        }

        return realtyStream;
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

        return realtyStream.filter(r -> allowFilters(r, filterParameters));
    }

    /**
     * Проверить, что модель соответствует фильтру.
     * @param realtyDao Модель данных.
     * @param filterParameters Фильтры.
     * @return true - если модель соответствует фильтру, иначе false.
     */
    private Boolean allowFilters(RealtyDao realtyDao, RealtyFilterParametersDto filterParameters){
        if (filterParameters == null){
            return true;
        }

        if (filterParameters.address != null && !filterParameters.address.isEmpty() && !realtyDao.address.contains(filterParameters.address)){
            return false;
        }

        if (filterParameters.fromCost != null && realtyDao.cost.compareTo(filterParameters.fromCost) < 0){
            return false;
        }

        if (filterParameters.toCost != null && realtyDao.cost.compareTo(filterParameters.toCost) > 0){
            return false;
        }

        if (filterParameters.fromTotalArea != null && realtyDao.totalArea < filterParameters.fromTotalArea){
            return false;
        }

        if (filterParameters.toTotalArea != null && realtyDao.totalArea > filterParameters.toTotalArea){
            return false;
        }

        return true;
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
