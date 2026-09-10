package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия показа данных.
 */
public class ShowDataStrategy implements MenuActionStrategy {
    private final ConsoleComponent showDataComponent;

    public ShowDataStrategy(ConsoleComponent showDataComponent) {
        this.showDataComponent = showDataComponent;
    }

    @Override
    public MenuResult execute() {
        // Показываем данные и остаемся в меню
        return MenuResult.stayInMenu(showDataComponent);
    }
}
