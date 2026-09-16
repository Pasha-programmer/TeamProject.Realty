package com.example.ConsoleUI.Menu.Contracts.Strategies;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuActionStrategy;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.ConsoleUI.Menu.SortingMenu;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Models.RealtyDto;

import java.util.Collection;
import java.util.Scanner;

/**
 * Стратегия перехода в меню сортировки данных.
 */
public class SortingDataStrategy implements MenuActionStrategy {
    private final ConsoleStageMenu sortingMenu;

    public SortingDataStrategy(Scanner scanner,
                               RealtyGetter realtyGetter,
                               RealtySorter realtySorter
    ) {
        sortingMenu = new SortingMenu(scanner, realtyGetter, realtySorter);
    }

    @Override
    public MenuResult execute() {
        return MenuResult.goToMenu(sortingMenu);
    }
}