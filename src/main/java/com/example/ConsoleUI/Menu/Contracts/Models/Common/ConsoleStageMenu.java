package com.example.ConsoleUI.Menu.Contracts.Models.Common;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Enums.EnumWithNumber;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;

/**
 * Базовое представление меню в консоли.
 */
public abstract class ConsoleStageMenu extends ConsoleComponent {

    protected ConsoleStageMenu (Scanner scanner){
        this.scanner = scanner;
    }

    protected final Scanner scanner;

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
                var input = scanner.nextLine().trim();
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

        var innerComponent = result.getConsoleComponent();
        if (innerComponent != null) {
            innerComponent.print();
            System.out.println(); // пустая строка для разделения
        }

        // Если есть следующее меню - запускаем его
        var nextMenu = result.getNextMenu();
        if (nextMenu != null) {
            nextMenu.run();
        }
    }
}
