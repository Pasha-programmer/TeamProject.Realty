package com.example.ConsoleUI.Menu.Contracts.Models.Common;

/**
 * Стратегия обработки выбора пункта меню.
 */
public interface MenuActionStrategy {
    /**
     * Выполнить действие, соответствующее выбору пользователя.
     * @return результат выполнения, определяющий дальнейшее поведение
     */
    MenuResult execute();
}
