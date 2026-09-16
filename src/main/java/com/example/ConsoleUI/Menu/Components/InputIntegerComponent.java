package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Components.Properties.InputIntegerComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

/**
 * Компонент ввода числа с плавающей точкой от пользователя.
 */
public class InputIntegerComponent extends InputComponent<Integer> {

    private final Integer minValue;

    private final Integer maxValue;

    public InputIntegerComponent(InputIntegerComponentProperties properties){

        super(properties.scanner, properties.label);

        this.minValue = properties.minValue;
        this.maxValue = properties.maxValue;
    }

    @Override
    public void print() {
        var tooltip = new StringBuilder("Введите целое число");

        if (minValue != null){
            tooltip.append(" больше ").append(minValue);
        }

        if (maxValue != null){
            if (minValue != null){
                tooltip.append(" и");
            }
            tooltip.append(" меньше ").append(maxValue);
        }

        if (!label.isEmpty()){
            System.out.print(label);
            tooltip.insert(0, " (");
            tooltip.append(')');
        }

        tooltip.append(": ");

        System.out.print(tooltip);
    }

    @Override
    public Integer read() {
        while (true){
            print();

            var value = readInteger();

            if (value != null){
                return value;
            }

            System.err.println("Не удалось получить корректное целое число. Попробуйте снова");
        }
    }

    protected Integer readInteger() {
        var input = scanner.nextLine().trim();

        // Проверка на пустой ввод
        if (input.isEmpty()) {
            return null;
        }

        // Удаляем пробелы
        input = input.replaceAll("\\s+", "");

        var value = tryParseInteger(input);

        if (value == null){
            return null;
        }

        // Проверка на допустимый диапазон (опционально)
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return null;
        }

        if (minValue != null && value < minValue){
            return null;
        }

        if (maxValue != null && value > maxValue){
            return null;
        }

        return value;
    }

    /**
     * Попытка парсинга числа с поддержкой разных форматов
     */
    private Integer tryParseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
