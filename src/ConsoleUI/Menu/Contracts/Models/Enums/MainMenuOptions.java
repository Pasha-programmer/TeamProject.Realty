package ConsoleUI.Menu.Contracts.Models.Enums;

/**
 * Перечисление опции для главного меню.
 */
public enum MainMenuOptions implements EnumWithNumber {
    CreateData(1),
    SortingData(2),
    ShowData(3),
    Exit(4);

    private final int value;

    MainMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

//    public static MainMenuOptions fromNumber(int value) throws IllegalArgumentException {
//        for (var s : values()) {
//            if (s.value == value) {
//                return s;
//            }
//        }
//        throw new IllegalArgumentException("Нет MainMenuOptions с значением " + value);
//    }
}
