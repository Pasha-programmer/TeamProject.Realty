package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Контракт обновления информации о недвижимости.
 */
public interface RealtyUpdater {

    /**
     * Добавить модели информации о недвижимости.
     * @param realtyDtos модели информации о недвижимости.
     * @return true - если добавление успешно, иначе false.
     */
    public Result<Boolean> addRealty(Collection<RealtyDto> realtyDtos);
}
