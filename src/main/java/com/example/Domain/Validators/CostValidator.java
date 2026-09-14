package com.example.Domain.Validators;

import java.math.BigDecimal;

import com.example.Domain.Contracts.Validators.FieldValidator;
import com.example.Domain.Models.BusinessError;

/**
 * Валидатор стоимости: должна быть строго больше нуля.
 */
public class CostValidator implements FieldValidator<BigDecimal> {

    @Override
    public BusinessError validate(BigDecimal cost) {
        if (cost == null) {
            return new BusinessError("Стоимость не может быть пустой.");
        }

        if (cost.compareTo(BigDecimal.ZERO) <= 0) {
            return new BusinessError("Стоимость должна быть больше нуля.");
        }

        return null;
    }
}
