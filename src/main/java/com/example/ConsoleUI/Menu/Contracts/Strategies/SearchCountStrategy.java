package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.RealtyFieldChoiceMenu;
import com.example.Domain.Contracts.Realty.RealtyGetter;

import java.util.Scanner;

/**
 * Стратегия поиска количества записей с фильтрацией.
 */
public class SearchCountStrategy implements MenuActionStrategy {

    private final ConsoleStageMenu realtyFieldChoiceMenu;

    public SearchCountStrategy(Scanner scanner, RealtyGetter realtyGetter) {
        realtyFieldChoiceMenu = new RealtyFieldChoiceMenu(scanner, realtyGetter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(realtyFieldChoiceMenu);
    }
}
