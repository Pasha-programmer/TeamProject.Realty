package ConsoleUI.Menu.Contracts.Models.Enums;

public interface EnumWithNumber {

    int getValue();

    static <T extends Enum<T> & EnumWithNumber> T fromNumber(Class<T> enumClass, int number) {
        for (T constant : enumClass.getEnumConstants()) {
            if (constant.getValue() == number) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Нет опции с значением: " + number);
    }
}
