package com.example.ConsoleUI.Menu.Contracts.Strategies.InputStrategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.GenerationMenu;
import com.example.ConsoleUI.Menu.RealtyUserInputMenu;
import com.example.Domain.Contracts.Realty.RealtyGenerator;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

import java.util.Scanner;

/**
 * Стратегия генерации данных
 */
public class GenerationInputStrategy implements MenuActionStrategy {

    private final ConsoleStageMenu nextMenu;

    public GenerationInputStrategy(Scanner scanner, RealtyUpdater realtyUpdater, RealtyGenerator realtyGenerator) {
        nextMenu = new GenerationMenu(scanner, realtyUpdater, realtyGenerator);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(nextMenu);
    }
}
