package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.SaveToFileMenu;
import com.example.Domain.Contracts.Realty.RealtyGetter;

import java.util.Scanner;

/**
 * Стратегия сохранения данных в JSON файл.
 */
public class SaveToFileStrategy implements MenuActionStrategy {
    public final ConsoleStageMenu saveToFileMenu;

    public SaveToFileStrategy(Scanner scanner, RealtyGetter realtyGetter) {
        this.saveToFileMenu = new SaveToFileMenu(scanner, realtyGetter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(saveToFileMenu);
    }
}
