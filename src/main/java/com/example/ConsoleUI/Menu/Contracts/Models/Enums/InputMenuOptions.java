package com.example.ConsoleUI.Menu.Contracts.Models.Enums;

/**
 * Перечисление опции для главного меню.
 */
public enum InputMenuOptions implements EnumWithNumber {
    UserInput(1),
    ImportFromFile(2),
    RandomData(3),
    Cancel(4);

    private final int value;

    InputMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
