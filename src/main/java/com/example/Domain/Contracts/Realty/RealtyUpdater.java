package com.example.Domain.Contracts.Realty;

import java.util.Collection;

import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Models.RealtyDto;

/**
 * Контракт обновления информации о недвижимости.
 */
public interface RealtyUpdater {

    /**
     * Добавить модели информации о недвижимости.
     * @param realtyDtos модели информации о недвижимости.
     * @return true - если добавление успешно, иначе false.
     */
    Result<Boolean> addRealty(Collection<RealtyDto> realtyDtos);

    /**
     * Добавить модель информации о недвижимости.
     * @param realtyDto модель информации о недвижимости.
     * @return true - если добавление успешно, иначе false.
     */
    Result<Boolean> addRealty(RealtyDto realtyDto);
}
