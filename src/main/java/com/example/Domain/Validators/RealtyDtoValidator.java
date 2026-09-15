package com.example.Domain.Validators;

import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;

/**
 * Валидатор модели недвижимости.
 * Собирает результат из валидаторов отдельных полей.
 */
public class RealtyDtoValidator extends Validator<RealtyDto> {

    private final AddressValidator addressValidator;
    private final MoneyValidator costValidator;
    private final TotalAreaValidator totalAreaValidator;

    public RealtyDtoValidator() {
        this(new AddressValidator(), new MoneyValidator(), new TotalAreaValidator());
    }

    public RealtyDtoValidator(
            AddressValidator addressValidator,
            MoneyValidator costValidator,
            TotalAreaValidator totalAreaValidator) {
        this.addressValidator = addressValidator;
        this.costValidator = costValidator;
        this.totalAreaValidator = totalAreaValidator;
    }

    @Override
    public BusinessError validate(RealtyDto model) {
        var addressError = addressValidator.validate(model.getAddress());
        if (addressError != null) {
            return addressError;
        }

        var costError = costValidator.validate(model.getCost());
        if (costError != null) {
            return costError;
        }

        return totalAreaValidator.validate(model.getTotalArea());
    }
}
