package com.example.ConsoleUI.Menu.Contracts.Models.Enums;

/**
 * Варианты сортировки недвижимости.
 */
public enum SortingMenuOptions implements EnumWithNumber {
    ByAddress(1),
    ByArea(2),
    ByCost(3),
    ByAllFields(4),
    ByEvenCost(5),
    Cancel(6);

    private final int value;

    SortingMenuOptions(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
