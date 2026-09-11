package com.example.ConsoleUI.Menu.Contracts.Strategies;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.RealtyFieldChoiceMenu;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

/**
 * Стратегия ручного ввода данных пользователем
 */
public class UserInputStrategy implements MenuActionStrategy {

    private final ConsoleStageMenu realtyUserInput;

    public UserInputStrategy(Scanner scanner, RealtyUpdater realtySetter) {
        realtyUserInput = new RealtyFieldChoiceMenu(scanner, realtySetter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(realtyFieldChoiceMenu);
    }
}
