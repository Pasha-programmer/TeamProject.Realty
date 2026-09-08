package com.example.Domain.Contracts.Monads;

import com.example.Domain.Models.BusinessError;

/**
 * Обертка над результатом.
 *
 * @param <V> Тип результата.
 */
public record Result<V>(V value, BusinessError error) {
    public Result(V value){
        this(value, null);
    }
}
