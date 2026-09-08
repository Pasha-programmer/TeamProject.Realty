package ConsoleUI.Menu.Contracts.Strategies;

import ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия выхода из приложения.
 */
public class ExitStrategy implements MenuActionStrategy {
    @Override
    public MenuResult execute() {
        return MenuResult.exit("Программа завершена.");
    }
}
