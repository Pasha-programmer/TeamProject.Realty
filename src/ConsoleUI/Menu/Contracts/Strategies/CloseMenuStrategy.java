package ConsoleUI.Menu.Contracts.Strategies;

import ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия закрытия меню.
 */
public class CloseMenuStrategy implements MenuActionStrategy {
    @Override
    public MenuResult execute() {
        return MenuResult.closeMenu();
    }
}
