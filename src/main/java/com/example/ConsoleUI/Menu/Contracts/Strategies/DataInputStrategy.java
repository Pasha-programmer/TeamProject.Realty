package com.example.ConsoleUI.Menu.Contracts.Strategies;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.RealtyDataInputMenu;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

/**
 * Стратегия создания данных.
 */
public class DataInputStrategy implements MenuActionStrategy {

    private final ConsoleStageMenu realtyDataInputMenu;

    public DataInputStrategy(Scanner scanner, RealtyUpdater realtySetter) {
        realtyDataInputMenu = new RealtyDataInputMenu(scanner, realtySetter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(realtyDataInputMenu);
    }
}
