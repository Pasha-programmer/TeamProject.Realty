package com.example.Domain.Contracts.Validators;

import com.example.Domain.Models.BusinessError;

/**
 * Базовый класс для валидатора сущности.
 * @param <T> Тип валидируемой сущности.
 */
public abstract class Validator<T> {

    /**
     * Провести валидацию сущности.
     * @param model Валидируемая сущность.
     * @return Null - если валидация прошла успешно, иначе модель ошибки бизнеса.
     */
    public abstract BusinessError validate(T model);
}
