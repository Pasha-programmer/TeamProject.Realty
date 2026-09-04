package ConsoleUI.Menu.Contracts.Common;

import ConsoleUI.Menu.Contracts.Models.Enums.EnumWithNumber;
import ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;

import java.util.Scanner;

/**
 * Базовое представление меню в консоли.
 */
public abstract class ConsoleStageMenu {

    protected static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Запустить отображение меню и ее обработку.
     */
    public abstract void run();

    /**
     * Отобразить ошибку о некорректном выборе опции из меню.
     */
    protected void printWrongChoiceError(){
        System.out.println("Некорректный выбор");
    }

    /**
     * Считать вводимое число пользователя.
     * @return Число, преобразованное в {@link MainMenuOptions}
     */
    protected <T extends Enum<T> & EnumWithNumber> T readOption(Class<T> menuOptionEnum) {
        while (true) {
            try {
                var input = SCANNER.nextLine().trim();
                var value = Integer.parseInt(input);
                return EnumWithNumber.fromNumber(menuOptionEnum, value);
            } catch (NumberFormatException e) {
                System.err.println("Введите корректное целое число");
            } catch (IllegalArgumentException e){
                System.err.println("Не удалось определить выбор");
            }
        }
    }
}
