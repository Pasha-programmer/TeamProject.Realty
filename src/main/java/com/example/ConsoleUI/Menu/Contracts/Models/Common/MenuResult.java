package com.example.ConsoleUI.Menu.Contracts.Models.Common;

import com.example.ConsoleUI.Menu.Components.ConsoleComponent;

/**
 * Результат выполнения стратегии меню.
 * Инкапсулирует информацию о том, что делать дальше.
 */
public class MenuResult {
    /**
     * Следующее меню для отображения
     */
    private final ConsoleStageMenu nextMenu;

    /**
     * Продолжать ли работу приложения
     */
    private final boolean shouldContinue;

    /**
     * Опциональное сообщение для пользователя
     */
    private final String message;

    /**
     * Опциональный компонент для пользователя.
     */
    private final ConsoleComponent consoleComponent;

    private MenuResult(ConsoleStageMenu nextMenu, boolean shouldContinue, ConsoleComponent consoleComponent, String message) {
        this.nextMenu = nextMenu;
        this.shouldContinue = shouldContinue;
        this.consoleComponent = consoleComponent;
        this.message = message;
    }

    /**
     * Перейти к следующему меню.
     */
    public static MenuResult goToMenu(ConsoleStageMenu menu) {
        return new MenuResult(menu, true, null, null);
    }

    /**
     * Продолжить работу в текущем меню с сообщением.
     */
    public static MenuResult stayInMenu(String message) {
        return new MenuResult(null, true, null, message);
    }

    /**
     * Продолжить работу в текущем меню с отображением компоненты.
     */
    public static MenuResult stayInMenu(ConsoleComponent consoleComponent) {
        return new MenuResult(null, true, consoleComponent, null);
    }

    /**
     * Завершить работу приложения с сообщением.
     */
    public static MenuResult exit(String message) {
        return new MenuResult(null, false, null, message);
    }

    /**
     * Закрыть текущее меню.
     */
    public static MenuResult closeMenu() {
        return new MenuResult(null, false, null, null);
    }

    public ConsoleStageMenu getNextMenu() {
        return nextMenu;
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }

    public ConsoleComponent getConsoleComponent() {
        return consoleComponent;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasMessage() {
        return message != null && !message.isEmpty();
    }
}
