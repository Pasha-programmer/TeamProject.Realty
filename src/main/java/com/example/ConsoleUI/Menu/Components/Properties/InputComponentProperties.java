package com.example.ConsoleUI.Menu.Components.Properties;

import java.util.Scanner;

/**
 * Свойства для компоненты ввода.
 */
public class InputComponentProperties {

    public InputComponentProperties(Scanner scanner){
        this.scanner = scanner;
    }

    public InputComponentProperties(Scanner scanner, String label){
        this(scanner);
        this.label = label;
    }

    /**
     * Сканнер.
     */
    public final Scanner scanner;

    /**
     * Заголовок.
     */
    public String label;
}
