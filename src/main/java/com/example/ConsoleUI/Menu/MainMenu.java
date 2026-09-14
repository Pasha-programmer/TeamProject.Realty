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
import com.example.ConsoleUI.Menu.Contracts.Strategies.DataInputStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.ExitStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.NotImplementedStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.SearchCountStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.ShowDataStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.SortingDataStrategy;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Contracts.Realty.RealtyUpdater;
import com.example.Domain.Models.RealtyDto;

public final class MainMenu extends ConsoleStageMenu {

    public MainMenu(
            Scanner scanner,
            RealtyGetter realtyGetter,
            RealtySorter realtySorter,
            RealtyUpdater realtyUpdater
    ) {
        super(scanner);
        realtyListComponent = new RealtyListComponent(realtyGetter);
        this.realtyGetter = realtyGetter;
        this.realtyUpdater = realtyUpdater;
        sortingMenu = new SortingMenu(scanner, realtyGetter, realtySorter, realtyListComponent);
    }

    private final ConsoleDataComponent<Collection<RealtyDto>> realtyListComponent;
    private final RealtyGetter realtyGetter;
    private final ConsoleStageMenu sortingMenu;
    private final RealtyUpdater realtyUpdater;

    private final static SortedMap<MainMenuOptions, String> menuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(MainMenuOptions.CreateData, "Создать данные о недвижимости"),
            Map.entry(MainMenuOptions.SortingData, "Отсортировать данные"),
            Map.entry(MainMenuOptions.ShowData, "Показать данные"),
            Map.entry(MainMenuOptions.Search, "Поиск"),
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
                    new DataInputStrategy(scanner, realtyUpdater);
                case MainMenuOptions.SortingData ->
                    new SortingDataStrategy(sortingMenu);
                case MainMenuOptions.ShowData ->
                    new ShowDataStrategy(realtyListComponent);
                case MainMenuOptions.Search ->
                    new SearchCountStrategy(scanner, realtyGetter);
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
