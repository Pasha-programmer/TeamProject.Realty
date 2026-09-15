package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Components.Properties.InputFilePathComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Компонент ввода пути до файла от пользователя.
 */
public class InputFilePathComponent extends InputComponent<Path> {

    private final String extension;

    public InputFilePathComponent(InputFilePathComponentProperties properties){
        super(properties.scanner);

        extension = properties.extension;
    }

    @Override
    public void print() {
        System.out.print("Введите путь до файла: ");
    }

    @Override
    public Path read() {
        print();

        while (true){
            var value = readFilePath();

            if (value != null){
                return value;
            }

            System.err.println("Некорректный путь");
        }
    }

    /**
     * Прочитать корректный путь до файла из консоли.
     * @return Путь до файла.
     */
    protected Path readFilePath() {
        var input = scanner.nextLine().trim();

        // Проверка на пустой ввод
        if (input.isEmpty()) {
            return null;
        }

        if (!input.endsWith("." + extension)){
            return null;
        }

        return Path.of(input);
    }
}
