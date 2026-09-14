package com.example.ConsoleUI.Menu.Contracts.Models.Common;

/**
 * Базовое представление консольного компонента с параметрами.
 * @param <T> Тип данных для параметризации.
 */
public abstract class ConsoleDataComponent<T> extends ConsoleComponent {

    /**
     * Нарисовать компонент.
     * @param data Дополнительные данные, требуемые для отрисовки.
     */
    public abstract void print(T data);
}
