package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия остаться в меню.
 */
public class StayInMenuStrategy implements MenuActionStrategy {

    private final String message;

    public StayInMenuStrategy(String message){
        this.message = message;
    }

    @Override
    public MenuResult execute() {
        return MenuResult.stayInMenu(message);
    }
}
