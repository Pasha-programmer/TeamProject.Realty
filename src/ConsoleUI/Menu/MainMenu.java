package ConsoleUI.Menu;

import ConsoleUI.Menu.Components.ConsoleComponent;
import ConsoleUI.Menu.Components.RealtyListComponent;
import ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;
import ConsoleUI.Menu.Contracts.Strategies.ExitStrategy;
import ConsoleUI.Menu.Contracts.Strategies.NotImplementedStrategy;
import ConsoleUI.Menu.Contracts.Strategies.SearchCountStrategy;
import ConsoleUI.Menu.Contracts.Strategies.ShowDataStrategy;
import Domain.Contracts.Realty.RealtyGetter;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class MainMenu extends ConsoleStageMenu {

    public MainMenu(RealtyGetter realtyGetter){
        realtyListComponent = new RealtyListComponent(realtyGetter);
        this.realtyGetter = realtyGetter;
    }

    private final ConsoleComponent realtyListComponent;
    private final RealtyGetter realtyGetter;

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
                    new NotImplementedStrategy("Создание данных");
                case MainMenuOptions.SortingData ->
                    new NotImplementedStrategy("Сортировка данных");
                case MainMenuOptions.ShowData ->
                    new ShowDataStrategy(realtyListComponent);
                case MainMenuOptions.Search ->
                    new SearchCountStrategy(realtyGetter);
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

        SCANNER.close();
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
