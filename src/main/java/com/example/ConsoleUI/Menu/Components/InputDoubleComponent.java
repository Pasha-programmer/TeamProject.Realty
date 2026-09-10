package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.Locale;

/**
 * Компонент ввода числа с плавающей точкой от пользователя.
 */
public class InputDoubleComponent extends InputComponent<Double> {

    private static final String NUMBER_MASK = "#,##0.0#";

    @Override
    public void print() {
        System.out.print("Введите число с плавающей точкой (формат: " + NUMBER_MASK + "): ");
    }

    @Override
    public Double read() {
        print();

        while (true){
            var value = readDouble();

            if (value != null){
                return value;
            }

            System.err.println("Не удалось получить нецелое число");
        }
    }

    protected Double readDouble() {
        String input = SCANNER.nextLine().trim();

        // Проверка на пустой ввод
        if (input.isEmpty()) {
            return null;
        }

        // Удаляем пробелы
        input = input.replaceAll("\\s+", "");

        // Пробуем парсить с поддержкой разных форматов
        var value = tryParseDouble(input);

        if (value != null) {
            // Проверка на допустимый диапазон (опционально)
            if (Double.isInfinite(value) || Double.isNaN(value)) {
                return null;
            }
        }

        return value;
    }

    /**
     * Попытка парсинга числа с поддержкой разных форматов
     */
    private Double tryParseDouble(String input) {
        var normalized = input.replace(',', '.');
        try {
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
