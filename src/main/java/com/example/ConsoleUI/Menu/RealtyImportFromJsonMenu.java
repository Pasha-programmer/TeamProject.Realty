package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Components.InputFilePathComponent;
import com.example.ConsoleUI.Menu.Components.Properties.InputFilePathComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.StayInMenuStrategy;
import com.example.Domain.Contracts.Realty.RealtyImporter;
import com.example.Domain.Validators.RealtyDtoValidator;
import com.example.Infrastructure.Services.External.JacksonJsonParserService;
import com.example.Infrastructure.Services.Realty.RealtyImportFromJsonService;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Этап меню импорта данных из JSON файла.
 */
public class RealtyImportFromJsonMenu extends ConsoleStageMenu {

    private final InputComponent<Path> inputFilePathComponent;
    private final RealtyImporter realtyImporter;

    public RealtyImportFromJsonMenu(Scanner scanner) {
        super(scanner);
        this.inputFilePathComponent = new InputFilePathComponent(new InputFilePathComponentProperties(scanner, "json"));
        this.realtyImporter = new RealtyImportFromJsonService(new RealtyDtoValidator(), new JacksonJsonParserService<>());
    }

    @Override
    public void run() {
        MenuActionStrategy menuActionStrategy;

        print();

        while (isRun()) {
            var filePath = inputFilePathComponent.read();

            var importResult = realtyImporter.importFromFile(filePath);

            if (!importResult.value() && importResult.error() != null){
                menuActionStrategy = new CloseMenuStrategy(importResult.error().errorMessage());
            }
            else {
                var isImportAgain = canImportAgain();

                menuActionStrategy = isImportAgain
                        ? new StayInMenuStrategy("Новый импорт из файла")
                        : new CloseMenuStrategy();
            }

            var menuResult = menuActionStrategy.execute();

            processResult(menuResult);
        }
    }

    @Override
    public void print() {
        System.out.println("\nИмпорт данных из файла:");
    }

    /**
     * Можно ли произвести импорт снова
     * @return true - если да, иначе false
     */
    private boolean canImportAgain(){
        System.out.println("\nХотите загрузить еще?(да/нет)");
        String choice = scanner.nextLine().trim().toLowerCase();

        return choice.toLowerCase().contains("да");
    }
}
