package com.example.Domain.Validators;

import java.math.BigDecimal;

import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;

/**
 * Валидатор модели недвижимости
 */
public class RealtyDtoValidator extends Validator<RealtyDto> {

    @Override
    public BusinessError validate(RealtyDto model) {
        var addressError = validateAddress(model.getAddress());
        if (addressError != null) {
            return addressError;
        }

        var costError = validateCost(model.getCost());
        if (costError != null) {
            return costError;
        }

        return validateTotalArea(model.getTotalArea());
    }

    /**
     * Проверка адреса: минимальный набор - улица и номер дома.
     * Номер дома - последний токен, содержащий цифру ("15", "15а", "15к2").
     * @param address проверяемый адрес.
     * @return ошибку, если адрес не содержит номер дома, иначе null.
     */
    public static BusinessError validateAddress(String address) {
        if (address == null || address.isBlank()) {
            return new BusinessError("Адрес не может быть пустым.");
        }

        var normalized = address.trim().replace(',', ' ').replace(';', ' ');
        var lastSeparator = normalized.lastIndexOf(' ');

        if (lastSeparator <= 0) {
            return new BusinessError("Адрес должен содержать улицу и номер дома (например: \"Ленина 15\").");
        }

        var houseNumber = normalized.substring(lastSeparator + 1);
        if (houseNumber.chars().noneMatch(Character::isDigit)) {
            return new BusinessError("Адрес должен содержать номер дома (например: \"Ленина 15\").");
        }

        return null;
    }

    /**
     * Проверка стоимости: должна быть строго больше нуля.
     */
    public static BusinessError validateCost(BigDecimal cost) {
        if (cost == null) {
            return new BusinessError("Стоимость не может быть пустой.");
        }

        if (cost.compareTo(BigDecimal.ZERO) <= 0) {
            return new BusinessError("Стоимость должна быть больше нуля.");
        }

        return null;
    }

    /**
     * Проверка площади: должна быть строго больше нуля.
     */
    public static BusinessError validateTotalArea(double totalArea) {
        if (totalArea <= 0) {
            return new BusinessError("Площадь должна быть больше нуля.");
        }

        return null;
    }
}
