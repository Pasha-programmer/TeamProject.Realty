package ConsoleUI.Menu;

import ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import ConsoleUI.Menu.Contracts.Models.Enums.SearchByFieldMenuOptions;
import ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import ConsoleUI.Menu.Contracts.Strategies.NotImplementedStrategy;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SearchByFieldMenu extends ConsoleStageMenu {

    public SearchByFieldMenu(){
    }

    private final static SortedMap<SearchByFieldMenuOptions, String> menuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(SearchByFieldMenuOptions.Address, "По адресу"),
            Map.entry(SearchByFieldMenuOptions.Cost, "По стоимости"),
            Map.entry(SearchByFieldMenuOptions.TotalArea, "По общей площади"),
            Map.entry(SearchByFieldMenuOptions.Cansel, "Отмена")
        )
    );

    @Override
    public void run() {
        while(isRun()){
            print();

            var choice = readOption(SearchByFieldMenuOptions.class);

            var action = switch (choice){
                case SearchByFieldMenuOptions.Address ->
                    new NotImplementedStrategy("Поиск по адрессу");
                case SearchByFieldMenuOptions.Cost ->
                    new NotImplementedStrategy("Поиск по стоимости");
                case SearchByFieldMenuOptions.TotalArea ->
                    new NotImplementedStrategy("Поиск по площади");
                case SearchByFieldMenuOptions.Cansel ->
                    new CloseMenuStrategy();
                default ->
                    new NotImplementedStrategy("" + choice.getValue());
            };

            // Выполняем стратегию и получаем результат
            var result = action.execute();

            // Обрабатываем результат
            processResult(result);
        }
    }

    @Override
    public void print() {
        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите опцию: ");
    }
}
