package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия закрытия меню.
 */
public class CloseMenuStrategy implements MenuActionStrategy {
    @Override
    public MenuResult execute() {
        return MenuResult.closeMenu();
    }
}
