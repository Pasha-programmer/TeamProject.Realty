package ConsoleUI.Menu.Contracts.Models.Common;

import ConsoleUI.Menu.Components.ConsoleComponent;
import ConsoleUI.Menu.Contracts.Models.Enums.EnumWithNumber;
import ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;

import java.util.Scanner;

/**
 * Базовое представление меню в консоли.
 */
public abstract class ConsoleStageMenu extends ConsoleComponent {

    protected static final Scanner SCANNER = new Scanner(System.in);

    private boolean isRun = true;

    protected boolean isRun() { return isRun; }

    protected void stopRun() { isRun = false; }

    /**
     * Запустить отображение меню и ее обработку.
     */
    public abstract void run();

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

    /**
     * Обработка результата выполнения стратегии.
     */
    protected void processResult(MenuResult result) {
        // Выводим сообщение, если оно есть
        if (result.hasMessage()) {
            System.out.println(result.getMessage());
            System.out.println(); // пустая строка для разделения
        }

        // Проверяем, нужно ли продолжать работу
        if (!result.shouldContinue()) {
            stopRun();
            return;
        }

        // Если есть следующее меню - запускаем его
        var nextMenu = result.getNextMenu();
        if (nextMenu != null) {
            nextMenu.run();
        }
    }
}
