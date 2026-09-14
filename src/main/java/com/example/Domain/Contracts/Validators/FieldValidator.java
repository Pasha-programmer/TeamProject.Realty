package com.example.Domain.Contracts.Validators;

import com.example.Domain.Models.BusinessError;

/**
 * Валидатор отдельного поля сущности.
 * @param <T> Тип валидируемого значения.
 */
public interface FieldValidator<T> {

    /**
     * Провести валидацию значения поля.
     * @param value Валидируемое значение.
     * @return Null - если валидация прошла успешно, иначе модель ошибки бизнеса.
     */
    BusinessError validate(T value);
}
