package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Components.InputFilePathComponent;
import com.example.ConsoleUI.Menu.Components.Properties.InputFilePathComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtyUploader;
import com.example.Infrastructure.Services.Realty.RealtyUploaderService;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Этап меню сохранения данных в файл.
 */
public class SaveToFileMenu extends ConsoleStageMenu {
    private final InputComponent<Path> inputFilePathComponent;
    private final RealtyUploader realtyUploader;

    public SaveToFileMenu(Scanner scanner, RealtyGetter realtyGetter) {
        super(scanner);
        this.inputFilePathComponent = new InputFilePathComponent(new InputFilePathComponentProperties(scanner, "json"));
        this.realtyUploader = new RealtyUploaderService(realtyGetter);
    }

    @Override
    public void run() {
        MenuActionStrategy menuActionStrategy;

        print();

        while (isRun()) {
            var filePath = inputFilePathComponent.read();
            var saveResult = realtyUploader.saveToFile(filePath);

            if (!saveResult.value() && saveResult.error() != null){
                menuActionStrategy = new CloseMenuStrategy(saveResult.error().errorMessage());
            } else {
                menuActionStrategy = new CloseMenuStrategy();
            }

            var menuResult = menuActionStrategy.execute();

            processResult(menuResult);
        }
    }

    @Override
    public void print() {
        System.out.println("Сохранение данных в файл");
    }
}
