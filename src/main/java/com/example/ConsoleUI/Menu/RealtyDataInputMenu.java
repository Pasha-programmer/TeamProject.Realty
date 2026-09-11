package com.example.ConsoleUI.Menu;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.InputMenuOptions;
import com.example.ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.DataInputStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.NotImplementedStrategy;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

public class RealtyDataInputMenu extends ConsoleStageMenu{

    private final RealtyUpdater realtySetter;

    public RealtyDataInputMenu(Scanner scanner, RealtyUpdater realtySetter){
        super(scanner);
        this.realtySetter = realtySetter;
    }
    
    private final static SortedMap<InputMenuOptions, String> dataInputMenuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(InputMenuOptions.UserInput, "Ручной ввод данных"),
            Map.entry(InputMenuOptions.ImportFromFile, "Импорт данных из файла"),
            Map.entry(InputMenuOptions.RandomData, "Генерация случайных данных"),
            Map.entry(InputMenuOptions.Cancel, "Отмена")
        )
    );
    
    @Override
    public void run() {
        print();
        var choice = readOption(InputMenuOptions.class);
        // Получаем стратегию для ввода данных
        var action = switch (choice){
            case InputMenuOptions.UserInput ->
                new DataInputStrategy(scanner, realtySetter);
            case InputMenuOptions.ImportFromFile->
                new NotImplementedStrategy("Импорт данных из файла");
            case InputMenuOptions.RandomData ->
                new NotImplementedStrategy("Генерация случайных данных");
            case InputMenuOptions.Cancel ->
                new CloseMenuStrategy();
        };

        // Выполняем стратегию и получаем результат
        var result = action.execute();

        // Обрабатываем результат
        processResult(result);

    }

    @Override
    public void print() {
        dataInputMenuOptionsMap.forEach((key,value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });
        System.out.print("Выберите опцию: ");
    }
}
