package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

import java.math.BigDecimal;

/**
 * Компонент ввода целого числа от пользователя.
 */
public class InputMoneyComponent extends InputComponent<BigDecimal> {

    private static final String MONEY_MASK = "###,###.##";

    @Override
    public void print() {
        System.out.print("Введите денежное значение (формат: " + MONEY_MASK + "): ");
    }

    @Override
    public BigDecimal read() {
        print();

        while (true){
            var value = readBigDecimal();

            if (value != null){
                return value;
            }

            System.err.println("Не удалось получить денежное значение");
        }
    }

    protected BigDecimal readBigDecimal() {
        var input = SCANNER.nextLine().trim();

        // Проверка на пустой ввод
        if (input.isEmpty()) {
            return null;
        }

        // Удаляем пробелы и разделители тысяч для парсинга
        var cleanedInput = input.replaceAll("[,\\s]", "");

        try {
            // Парсим как BigDecimal
            var value = new BigDecimal(cleanedInput);

            // Проверяем, что значение не отрицательное (если нужно)
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                System.err.println("Значение не может быть отрицательным");
                return null;
            }

            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
