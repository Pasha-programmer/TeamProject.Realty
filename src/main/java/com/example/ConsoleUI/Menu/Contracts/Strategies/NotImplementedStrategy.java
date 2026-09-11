package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия для нереализованных функций.
 */
public class NotImplementedStrategy implements MenuActionStrategy {
    private final String featureName;

    public NotImplementedStrategy(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public MenuResult execute() {
        return MenuResult.stayInMenu("Функция '" + featureName + "' пока недоступна");
    }
}
