package com.example.Domain.Validators;

import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;

/**
 * Валидатор адреса: минимальный набор - улица и номер дома.
 * Номер дома - последний токен, содержащий цифру ("15", "15а", "15к2").
 */
public class AddressValidator extends Validator<String> {

    @Override
    public BusinessError validate(String address) {
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
}
