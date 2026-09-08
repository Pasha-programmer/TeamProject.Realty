package ConsoleUI.Menu.Contracts.Strategies;

import ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import ConsoleUI.Menu.RealtyFieldChoiceMenu;
import Domain.Contracts.Realty.RealtyGetter;

/**
 * Стратегия поиска количества записей с фильтрацией.
 */
public class SearchCountStrategy implements MenuActionStrategy {

    private final ConsoleStageMenu realtyFieldChoiceMenu;

    public SearchCountStrategy(RealtyGetter realtyGetter) {
        realtyFieldChoiceMenu = new RealtyFieldChoiceMenu(realtyGetter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(realtyFieldChoiceMenu);
    }
}
