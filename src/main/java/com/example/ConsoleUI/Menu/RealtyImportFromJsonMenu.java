package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Components.RealtyImportFromJsonComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.Contracts.Strategies.ShowDataStrategy;

import java.util.Scanner;

public class RealtyImportFromJsonMenu extends ConsoleStageMenu {
    private final RealtyImportFromJsonComponent realtyImportFromJsonComponent;
    private final Scanner scanner;

    public RealtyImportFromJsonMenu(Scanner scanner) {
        super(scanner);
        realtyImportFromJsonComponent = new RealtyImportFromJsonComponent(scanner);
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (isRun()) {
            print();

            realtyImportFromJsonComponent.uploadData();
            var action = new ShowDataStrategy(realtyImportFromJsonComponent);

            processResult(action.execute());

            System.out.println("\nХотите загрузить еще?(yes/no)");
            String pathStr = scanner.nextLine().trim();
            if (pathStr.isEmpty() || pathStr.contains("n")) {
                processResult(MenuResult.closeMenu());
            }
        }
    }

    @Override
    public void print() {
        System.out.print("\nВведите абсолютный путь до файла для импорта сущностей:");
    }
}
