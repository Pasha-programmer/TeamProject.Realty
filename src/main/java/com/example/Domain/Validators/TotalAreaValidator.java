package com.example.Domain.Validators;

import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;

/**
 * Валидатор площади: должна быть строго больше нуля.
 */
public class TotalAreaValidator extends Validator<Double> {

    @Override
    public BusinessError validate(Double totalArea) {
        if (totalArea == null) {
            return new BusinessError("Площадь не может быть пустой.");
        }

        if (totalArea <= 0) {
            return new BusinessError("Площадь должна быть больше нуля.");
        }

        return null;
    }
}
