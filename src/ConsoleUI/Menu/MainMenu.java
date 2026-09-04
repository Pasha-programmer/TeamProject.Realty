package ConsoleUI.Menu;

import ConsoleUI.Menu.Components.ConsoleComponent;
import ConsoleUI.Menu.Components.RealtyListComponent;
import ConsoleUI.Menu.Contracts.Common.ConsoleStageMenu;
import ConsoleUI.Menu.Contracts.Models.Enums.MainMenuOptions;
import Domain.Contracts.Realty.RealtyGetter;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class MainMenu extends ConsoleStageMenu {

    public MainMenu(RealtyGetter realtyGetter){
        realtyListComponent = new RealtyListComponent(realtyGetter);
    }

    private final ConsoleComponent realtyListComponent;

    private final static SortedMap<MainMenuOptions, String> menuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(MainMenuOptions.CreateData, "Создать данные о недвижимости"),
            Map.entry(MainMenuOptions.SortingData, "Отсортировать данные"),
            Map.entry(MainMenuOptions.ShowData, "Показать данные"),
            Map.entry(MainMenuOptions.Exit, "Выход")
        )
    );

    private static boolean IS_RUN = true;

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

        while(IS_RUN){
            print();

            var choice = readOption(MainMenuOptions.class);

            switch (choice){
                case MainMenuOptions.CreateData ->
                    throw new UnsupportedOperationException();
                case MainMenuOptions.SortingData ->
                    throw new UnsupportedOperationException();
                case MainMenuOptions.ShowData ->
                    realtyListComponent.print();
                case MainMenuOptions.Exit ->
                    onExit();
                default ->
                    printWrongChoiceError();
            }
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

    /**
     * Обработчик выхода из приложения.
     */
    private void onExit(){
        IS_RUN = false;
        System.out.println("Программа завершена.");
    }
}
