package com.example.ConsoleUI.Menu.Components.Properties;

import com.example.ConsoleUI.Menu.Components.InputIntegerComponent;

import java.util.Scanner;

/**
 * Свойства для компоненты {@link InputIntegerComponent}
 */
public class InputIntegerComponentProperties extends InputComponentProperties {

    public InputIntegerComponentProperties(Scanner scanner){
        super(scanner);
    }

    public InputIntegerComponentProperties(Scanner scanner, String label){
        super(scanner, label);
    }

    public InputIntegerComponentProperties(Scanner scanner, String label, Integer minValue, Integer maxValue){
        this(scanner, label);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Минимальное валидное значение.
     */
    public Integer minValue;

    /*
     * Максимальное валидное значение.
     */
    public Integer maxValue;
}
