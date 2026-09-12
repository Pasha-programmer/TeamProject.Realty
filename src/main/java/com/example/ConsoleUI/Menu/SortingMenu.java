package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.SortingMenuOptions;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Models.RealtyDto;
import com.example.Infrastructure.Services.Realty.Sorting.AddressSortingStrategy;
import com.example.Infrastructure.Services.Realty.Sorting.AllFieldsSortingStrategy;
import com.example.Infrastructure.Services.Realty.Sorting.AreaSortingStrategy;
import com.example.Infrastructure.Services.Realty.Sorting.CostSortingStrategy;

import java.util.*;

/**
 * Меню выбора способа сортировки недвижимости.
 */
public class SortingMenu extends ConsoleStageMenu {

    public SortingMenu(
            Scanner scanner,
            RealtyGetter realtyGetter,
            RealtySorter realtySorter,
            ConsoleDataComponent<Collection<RealtyDto>> realtyListComponent
    ) {
        super(scanner);
        this.realtyGetter = realtyGetter;
        this.realtySorter = realtySorter;
        this.realtyListComponent = realtyListComponent;
    }

    private final RealtyGetter realtyGetter;
    private final RealtySorter realtySorter;
    private final ConsoleDataComponent<Collection<RealtyDto>> realtyListComponent;

    private final static SortedMap<SortingMenuOptions, String> menuOptionsMap = new TreeMap<>(
            Map.ofEntries(
                    Map.entry(SortingMenuOptions.ByAddress, "По адресу"),
                    Map.entry(SortingMenuOptions.ByArea, "По площади"),
                    Map.entry(SortingMenuOptions.ByCost, "По стоимости"),
                    Map.entry(SortingMenuOptions.ByAllFields, "По всем полям")
            )
    );

    @Override
    public void run() {
        print();

        var choice = readOption(SortingMenuOptions.class);

        var sortingStrategy = switch (choice) {
            case ByAddress -> new AddressSortingStrategy();
            case ByArea -> new AreaSortingStrategy();
            case ByCost -> new CostSortingStrategy();
            case ByAllFields -> new AllFieldsSortingStrategy();
        };

        var comparator = sortingStrategy.getComparator();

        var realty = realtyGetter.getRealty(null);

        var sortedRealty = realtySorter.sort(realty, comparator);

        realtyListComponent.print(sortedRealty);
    }

    @Override
    public void print() {
        System.out.println("\nМеню сортировки:");

        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите способ сортировки: ");
    }
}
