package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Components.Properties.InputComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Компонент ввода ответа да или нет.
 */
public class InputBooleanComponent extends InputComponent<Boolean> {

    public InputBooleanComponent(InputComponentProperties inputComponentProperties){
        super(inputComponentProperties.scanner, inputComponentProperties.label);
    }

    private final Collection<String> trueAnswers = List.of("да", "д", "1");

    private final Collection<String> falseAnswers = List.of("нет", "н", "0");

    @Override
    public void print() {
        System.out.print("Введите да/нет: ");
    }

    @Override
    public Boolean read() {

        if (!label.isEmpty()){
            System.out.println(label);
        }

        while (true){
            print();
            var value = readBoolean();

            if (value != null){
                return value;
            }

            System.err.println("Не удалось получить ответ. Попробуйте снова.");
        }
    }

    protected Boolean readBoolean() {
        String input = scanner.nextLine().trim();

        // Проверка на пустой ввод
        if (input.isEmpty()) {
            return null;
        }

        // Удаляем пробелы
        input = input.replaceAll("\\s+", "");

        // Пробуем парсить с поддержкой разных форматов
        return tryParseBoolean(input);
    }

    /**
     * Попытка парсинга ответа.
     */
    private Boolean tryParseBoolean(String input) {

        if (trueAnswers.contains(input)){
            return true;
        }

        if (falseAnswers.contains(input)){
            return false;
        }

        try {
            return Boolean.parseBoolean(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
