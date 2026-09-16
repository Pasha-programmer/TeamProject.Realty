package com.example.ConsoleUI.Menu.Components.Properties;

import com.example.ConsoleUI.Menu.Components.InputFilePathComponent;

import java.util.Scanner;

/**
 * Свойства компоненты {@link InputFilePathComponent}
 */
public class InputFilePathComponentProperties extends InputComponentProperties {

    public InputFilePathComponentProperties(Scanner scanner, String extension){
        super(scanner);
        this.extension = extension;
    }

    /**
     * Требуемое расширение файла.
     */
    public String extension;
}
