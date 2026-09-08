package ConsoleUI.Menu.Contracts.Models.Enums;

/**
 * Перечисление опции для меню поиска.
 */
public enum SearchByFieldMenuOptions implements EnumWithNumber {
    Address(1),
    Cost(2),
    TotalArea(3),
    Cansel(4);

    private final int value;

    SearchByFieldMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
