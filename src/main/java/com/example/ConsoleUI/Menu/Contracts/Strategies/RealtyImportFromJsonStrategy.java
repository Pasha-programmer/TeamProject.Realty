package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.RealtyImportFromJsonMenu;

import java.util.Scanner;

/**
 * Стратегия импорта данных из JSON файла.
 */
public class RealtyImportFromJsonStrategy implements MenuActionStrategy {
    private final ConsoleStageMenu realtyImportFromJsonMenu;

    public RealtyImportFromJsonStrategy(Scanner scanner) {
        realtyImportFromJsonMenu = new RealtyImportFromJsonMenu(scanner);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(realtyImportFromJsonMenu);
    }
}
