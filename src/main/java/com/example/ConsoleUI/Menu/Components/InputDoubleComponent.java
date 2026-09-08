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

    private static final DecimalFormat FORMATTER;

    static {
        // Настройка форматтера для поддержки разных локалей
        var symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');

        FORMATTER = new DecimalFormat(NUMBER_MASK, symbols);
        FORMATTER.setGroupingUsed(true);
        FORMATTER.setGroupingSize(3);
        FORMATTER.setParseBigDecimal(false);
    }


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
        Double value = tryParseDouble(input);

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
        // Попытка 1: Стандартный парсинг
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // Продолжаем пробовать другие форматы
        }

        // Попытка 2: Парсинг с форматтером
        try {
            var pos = new ParsePosition(0);
            Number number = FORMATTER.parse(input, pos);
            if (pos.getIndex() == input.length() && number != null) {
                return number.doubleValue();
            }
        } catch (Exception e) {
            // Продолжаем
        }

        // Попытка 3: Замена запятой на точку (для европейского формата)
        try {
            String normalized = input.replace(',', '.');
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            // Продолжаем
        }

        // Попытка 4: Удаление всех разделителей тысяч
        try {
            String cleaned = input.replaceAll("[,\\s]", "");
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Метод для форматирования числа с маской
     */
    public static String formatDouble(Double value) {
        if (value == null) {
            return "";
        }
        return FORMATTER.format(value);
    }

    /**
     * Метод для форматирования с указанным количеством знаков после запятой
     */
    public static String formatDouble(Double value, int decimalPlaces) {
        if (value == null) {
            return "";
        }

        StringBuilder pattern = new StringBuilder("#,##0");
        if (decimalPlaces > 0) {
            pattern.append(".");
            pattern.append("0".repeat(decimalPlaces));
        }

        DecimalFormat customFormatter = new DecimalFormat(pattern.toString());
        customFormatter.setGroupingUsed(true);
        customFormatter.setGroupingSize(3);

        return customFormatter.format(value);
    }
}
