package com.example.ConsoleUI.Menu;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.InputMenuOptions;
import com.example.ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.InputStrategies.GenerationInputStrategy;
import com.example.ConsoleUI.Menu.Contracts.Strategies.InputStrategies.UserInputStrategy;
import com.example.Domain.Contracts.Realty.RealtyGenerator;
import com.example.ConsoleUI.Menu.Contracts.Strategies.RealtyImportFromJsonStrategy;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

/**
 * Этап меню выбора ввода данных. Возможен: 
 * Ручной ввод
 * Импорт из Json-файла
 * Генерация случайных данных
 */
public class RealtyDataInputMenu extends ConsoleStageMenu{

    private final RealtyUpdater realtySetter;
    private final RealtyGenerator realtyGenerator;

    public RealtyDataInputMenu(Scanner scanner, RealtyUpdater realtySetter, RealtyGenerator realtyGenerator){
        super(scanner);
        this.realtySetter = realtySetter;
        this.realtyGenerator = realtyGenerator;
    }
    
    private final static SortedMap<InputMenuOptions, String> dataInputMenuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(InputMenuOptions.UserInput, "Ручной ввод данных"),
            Map.entry(InputMenuOptions.ImportFromFile, "Загрузить данные из файла"),
            Map.entry(InputMenuOptions.RandomData, "Генерация случайных данных"),
            Map.entry(InputMenuOptions.Cancel, "Отмена")
        )
    );
    
    @Override
    public void run() {
        while (isRun()){
            print();

            var choice = readOption(InputMenuOptions.class);
            // Получаем стратегию для ввода данных
            var action = switch (choice){
                case InputMenuOptions.UserInput ->
                    new UserInputStrategy(scanner, realtySetter);
                case InputMenuOptions.ImportFromFile->
                    new RealtyImportFromJsonStrategy(scanner);
                case InputMenuOptions.RandomData ->
                    new GenerationInputStrategy(scanner, realtySetter, realtyGenerator);
                case InputMenuOptions.Cancel ->
                    new CloseMenuStrategy();
            };

            // Выполняем стратегию и получаем результат
            var result = action.execute();

            // Обрабатываем результат
            processResult(result);
        }
    }

    @Override
    public void print() {
        System.out.println("\nМеню создания данных о недвижимости:");

        dataInputMenuOptionsMap.forEach((key,value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите опцию: ");
    }
}
