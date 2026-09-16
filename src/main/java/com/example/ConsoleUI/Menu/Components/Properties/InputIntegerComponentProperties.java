package com.example.ConsoleUI.Menu.Components.Properties;

import com.example.ConsoleUI.Menu.Components.InputIntegerComponent;

import java.util.Scanner;

/**
 * Свойства для компоненты {@link InputIntegerComponent}
 */
public class InputIntegerComponentProperties {

    public InputIntegerComponentProperties(Scanner scanner){
        this.scanner = scanner;
    }

    public InputIntegerComponentProperties(Scanner scanner, Integer minValue, Integer maxValue){
        this(scanner);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Сканер для чтения с консоли.
     */
    public Scanner scanner;

    /**
     * Минимальное валидное значение.
     */
    public Integer minValue;

    /*
     * Максимальное валидное значение.
     */
    public Integer maxValue;
}
