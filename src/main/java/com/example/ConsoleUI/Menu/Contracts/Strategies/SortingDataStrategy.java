package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия перехода в меню сортировки данных.
 */
public class SortingDataStrategy implements MenuActionStrategy {
    private final ConsoleStageMenu sortingMenu;

    public SortingDataStrategy(ConsoleStageMenu sortingMenu) {
        this.sortingMenu = sortingMenu;
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(sortingMenu);
    }
}