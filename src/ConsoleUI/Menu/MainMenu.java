package ConsoleUI.Menu;

import ConsoleUI.Menu.Contract.ConsoleStageMenu;
import ConsoleUI.Menu.Contract.MainMenuOptions;

import java.util.Map;
import java.util.Scanner;

public final class MainMenu extends ConsoleStageMenu {

    private final static Map<MainMenuOptions, String> menuOptionsMap = Map.ofEntries(
        Map.entry(MainMenuOptions.CreateData, "Создать данные о недвижимости"),
        Map.entry(MainMenuOptions.SortingData, "Отсортировать данные"),
        Map.entry(MainMenuOptions.ShowData, "Показать данные"),
        Map.entry(MainMenuOptions.Exit, "Выход")
    );

    private static final Scanner SCANNER = new Scanner(System.in);

    private static boolean IS_RUN = true;

    @Override
    public void run() {
        printHeader();

        while(IS_RUN){
            printMainMenu();

            var choice = readInput(1, 5);

            switch (choice){
                case MainMenuOptions.CreateData ->
                    throw new UnsupportedOperationException();
                case MainMenuOptions.SortingData ->
                    throw new UnsupportedOperationException();
                case MainMenuOptions.ShowData ->
                    throw new UnsupportedOperationException();
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
     * Отобразить меню.
     */
    private void printMainMenu() {
        System.out.println("\nГлавное меню:");

        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите опцию: ");
    }

    /**
     * Считать вводимое число пользователя.
     * @param min Минимально возможное число.
     * @param max Максимальное возможное число.
     * @return Число, преобразованное в {@link MainMenuOptions}
     */
    private MainMenuOptions readInput(int min, int max) {
        while (true) {
            try {
                var input = SCANNER.nextLine().trim();
                var value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return MainMenuOptions.fromNumber(value);
                }
                System.err.printf("Введите число от %d до %d: ", min, max);
            } catch (NumberFormatException e) {
                System.err.println("Введите корректное целое число");
            } catch (IllegalArgumentException e){
                System.err.println("Не удалось определить выбор");
            }
        }
    }

    private void onExit(){
        IS_RUN = false;
        System.out.println("Программа завершена.");
    }
}
