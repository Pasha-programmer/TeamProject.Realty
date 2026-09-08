package ConsoleUI.Menu.Contracts.Strategies;

import ConsoleUI.Menu.Components.ConsoleComponent;
import ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import ConsoleUI.Menu.Contracts.Models.Common.MenuResult;

/**
 * Стратегия показа данных.
 */
public class ShowDataStrategy implements MenuActionStrategy {
    private final ConsoleComponent realtyListComponent;

    public ShowDataStrategy(ConsoleComponent realtyListComponent) {
        this.realtyListComponent = realtyListComponent;
    }

    @Override
    public MenuResult execute() {
        // Показываем данные и остаемся в меню
        return MenuResult.stayInMenu(realtyListComponent);
    }
}
