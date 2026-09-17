package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.ExportToJsonMenu;
import com.example.Domain.Contracts.Realty.RealtyExporter;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtySorter;

import java.util.Scanner;

/**
 * Стратегия сохранения данных в JSON файл.
 */
public class RealtyExportToJsonStrategy implements MenuActionStrategy {
    public final ConsoleStageMenu exportToJsonMenu;

    public RealtyExportToJsonStrategy(Scanner scanner, RealtyExporter realtyExporter, RealtyGetter realtyGetter, RealtySorter realtySorter) {
        this.exportToJsonMenu = new ExportToJsonMenu(scanner, realtyExporter, realtyGetter, realtySorter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(exportToJsonMenu);
    }
}
