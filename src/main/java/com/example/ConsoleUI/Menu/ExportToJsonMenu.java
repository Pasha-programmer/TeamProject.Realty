package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Components.InputBooleanComponent;
import com.example.ConsoleUI.Menu.Components.InputFilePathComponent;
import com.example.ConsoleUI.Menu.Components.Properties.InputComponentProperties;
import com.example.ConsoleUI.Menu.Components.Properties.InputFilePathComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtyExporter;
import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Infrastructure.Services.Realty.RealtyExportToJsonService;
import com.example.Infrastructure.Services.Realty.Sorting.AllFieldsSortingStrategy;
import com.example.Infrastructure.Services.Realty.Sorting.ComparatorRealtySortingStrategy;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Этап меню сохранения данных в файл.
 */
public class ExportToJsonMenu extends ConsoleStageMenu {

    private final InputComponent<Path> inputFilePathComponent;
    private final InputComponent<Boolean> inputBooleanComponent;
    private final RealtyExporter realtyExporter;
    private final RealtyGetter realtyGetter;
    private final RealtySorter realtySorter;

    public ExportToJsonMenu(Scanner scanner, RealtyExporter realtyExporter, RealtyGetter realtyGetter, RealtySorter realtySorter) {
        super(scanner);

        this.inputFilePathComponent = new InputFilePathComponent(new InputFilePathComponentProperties(scanner, "json"));
        this.inputBooleanComponent = new InputBooleanComponent(new InputComponentProperties(scanner, "Нужно ли сортировать?"));
        this.realtyGetter = realtyGetter;
        this.realtyExporter = realtyExporter;
        this.realtySorter = realtySorter;
    }

    @Override
    public void run() {
        MenuActionStrategy menuActionStrategy;

        print();

        var data = realtyGetter.getRealty(null);

        while (isRun()) {
            var filePath = inputFilePathComponent.read();

            var needSort = inputBooleanComponent.read();

            if (needSort){
                var realtySortingStrategy = new ComparatorRealtySortingStrategy(
                        realtySorter,
                        new AllFieldsSortingStrategy());

                data = realtySortingStrategy.sort(data);
            }

            var saveResult = realtyExporter.saveToFile(data, filePath);

            if (!saveResult.value() && saveResult.error() != null){
                menuActionStrategy = new CloseMenuStrategy(saveResult.error().errorMessage());
            } else {
                menuActionStrategy = new CloseMenuStrategy("Файл успешно сохранен.");
            }

            var menuResult = menuActionStrategy.execute();

            processResult(menuResult);
        }
    }

    @Override
    public void print() {
        System.out.println("Экспорт данных в файл");
    }
}
