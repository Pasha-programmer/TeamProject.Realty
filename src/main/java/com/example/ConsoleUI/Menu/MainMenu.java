package com.example.ConsoleUI.Menu;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.ConsoleUI.Menu.Components.RealtyListComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;
import com.example.ConsoleUI.Menu.Contracts.Strategies.*;
import com.example.Domain.Contracts.Realty.*;
import com.example.Domain.Models.RealtyDto;

public final class MainMenu extends ConsoleStageMenu {

    public MainMenu(
            Scanner scanner,
            RealtyGetter realtyGetter,
            RealtySorter realtySorter,
            RealtyUpdater realtyUpdater,
            RealtyGenerator realtyGenerator,
            RealtyImporter realtyImporter,
            RealtyExporter realtyExporter
    ) {
        super(scanner);
        realtyListComponent = new RealtyListComponent(realtyGetter);
        this.realtyGetter = realtyGetter;
        this.realtySorter = realtySorter;
        this.realtyUpdater = realtyUpdater;
        this.realtyGenerator = realtyGenerator;
        this.realtyImporter = realtyImporter;
        this.realtyExporter = realtyExporter;
    }

    private final ConsoleDataComponent<Collection<RealtyDto>> realtyListComponent;
    private final RealtyGetter realtyGetter;
    private final RealtySorter realtySorter;
    private final RealtyUpdater realtyUpdater;
    private final RealtyGenerator realtyGenerator;
    private final RealtyImporter realtyImporter;
    private final RealtyExporter realtyExporter;

    private final static SortedMap<MainMenuOptions, String> menuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(MainMenuOptions.CreateData, "Создать данные о недвижимости"),
            Map.entry(MainMenuOptions.SortingData, "Отсортировать данные"),
            Map.entry(MainMenuOptions.ShowData, "Показать данные"),
            Map.entry(MainMenuOptions.Search, "Поиск"),
            Map.entry(MainMenuOptions.ExportData, "Сохранить в файл"),
            Map.entry(MainMenuOptions.Exit, "Выход")
        )
    );

    @Override
    public void print() {
        System.out.println("\nГлавное меню:");

        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите опцию: ");
    }

    @Override
    public void run() {
        printHeader();

        while(isRun()){
            print();

            var choice = readOption(MainMenuOptions.class);

            // Получаем стратегию для выбранной опции
            var action = switch (choice){
                case MainMenuOptions.CreateData ->
                    new DataInputStrategy(scanner, realtyUpdater, realtyGenerator, realtyImporter);
                case MainMenuOptions.SortingData ->
                    new SortingDataStrategy(scanner, realtyGetter, realtySorter);
                case MainMenuOptions.ShowData ->
                    new ShowDataStrategy(realtyListComponent);
                case MainMenuOptions.Search ->
                    new SearchCountStrategy(scanner, realtyGetter);
                case MainMenuOptions.ExportData ->
                    new RealtyExportToJsonStrategy(scanner, realtyExporter, realtyGetter, realtySorter);
                case MainMenuOptions.Exit ->
                    new ExitStrategy();
                default ->
                    new NotImplementedStrategy("" + choice.getValue());
            };

            // Выполняем стратегию и получаем результат
            var result = action.execute();

            // Обрабатываем результат
            processResult(result);
        }
    }

    /**
     * Отобразить заголовок приложения.
     */
    private void printHeader(){
        var title = "\tПРИЛОЖЕНИЕ ДЛЯ СОРТИРОВКИ НЕДВИЖИМОСТИ";
        var decor = "=".repeat(8 + title.length());
        System.out.println(decor);
        System.out.println(title);
        System.out.println(decor);
    }
}
