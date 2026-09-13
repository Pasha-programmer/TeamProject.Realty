package com.example.ConsoleUI.Menu.Contracts.Models.Enums;

/**
 * Перечисление опции для главного меню.
 */
public enum MainMenuOptions implements EnumWithNumber {
    CreateData(1),
    SortingData(2),
    ShowData(3),
    Search(4),
    ImportData(5),
    Exit(6);

    private final int value;

    MainMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
