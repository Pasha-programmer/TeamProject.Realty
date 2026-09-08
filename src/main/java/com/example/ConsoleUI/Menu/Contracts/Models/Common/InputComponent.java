package com.example.ConsoleUI.Menu.Contracts.Models.Common;

import com.example.ConsoleUI.Menu.Components.ConsoleComponent;

import java.util.Scanner;

/**
 * Базовое представление консольного компонента ввода.
 */
public abstract class InputComponent<T> extends ConsoleComponent {

    protected static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Считать ввод
     */
    public abstract T read();
}
