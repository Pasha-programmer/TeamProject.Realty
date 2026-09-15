package com.example.ConsoleUI.Menu.Components.Properties;

import com.example.ConsoleUI.Menu.Components.InputFilePathComponent;

import java.util.Scanner;

/**
 * Свойства компоненты {@link InputFilePathComponent}
 */
public class InputFilePathComponentProperties {

    public InputFilePathComponentProperties(Scanner scanner, String extension){
        this.scanner = scanner;
        this.extension = extension;
    }

    /**
     * Сканнер.
     */
    public Scanner scanner;

    /**
     * Требуемое расширение файла.
     */
    public String extension;
}
