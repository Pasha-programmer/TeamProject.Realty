package com.example.ConsoleUI.Menu.Contracts.Models.Common;

import java.util.Scanner;

/**
 * Базовое представление консольного компонента ввода.
 */
public abstract class InputComponent<T> extends ConsoleComponent {

    protected InputComponent(Scanner scanner){
        this.scanner = scanner;
    }

    protected InputComponent(Scanner scanner, String label){
        this.scanner = scanner;
        this.label = label;
    }

    protected final Scanner scanner;
    protected String label;

    /**
     * Считать ввод
     */
    public abstract T read();
}
