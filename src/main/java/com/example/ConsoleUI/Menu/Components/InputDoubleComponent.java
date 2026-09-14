package com.example.ConsoleUI.Menu.Components;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

/**
 * Компонент ввода числа с плавающей точкой от пользователя.
 */
public class InputDoubleComponent extends InputComponent<Double> {

    public InputDoubleComponent(Scanner scanner){
        super(scanner);
    }

    private static final String NUMBER_MASK = "#,##0.0#";

    @Override
    public void print() {
        System.out.print("Введите число с плавающей точкой (формат: " + NUMBER_MASK + "): ");
    }

    @Override
    public Double read() {
        while (true){
            print();
            var value = readDouble();

            if (value != null){
                return value;
            }

            System.err.println("Не удалось получить нецелое число. Попробуйте снова. ");
        }
    }

    protected Double readDouble() {
        String input = scanner.nextLine().trim();

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
     * Попытка парсинга числа с поддержкой разных форматов.
     * Последняя встреченная точка или запятая считается десятичным разделителем,
     * все остальные точки и запятые отбрасываются как разделители разрядов.
     */
    private Double tryParseDouble(String input) {

        var lastDot = input.lastIndexOf('.');
        var lastComma = input.lastIndexOf(',');
        var decimalSeparator = Math.max(lastDot, lastComma);

        String normalized;
        if (decimalSeparator < 0) {
            normalized = input;
        } else {
            var integerPart = input.substring(0, decimalSeparator).replace(".", "").replace(",", "");
            var fractionalPart = input.substring(decimalSeparator + 1).replace(".", "").replace(",", "");
            normalized = integerPart + "." + fractionalPart;
        }

        try {
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
