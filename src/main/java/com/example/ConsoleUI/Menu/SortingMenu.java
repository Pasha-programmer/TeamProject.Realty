package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.SortingMenuOptions;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Contracts.Realty.RealtySortingStrategy;
import com.example.Domain.Models.RealtyDto;
import com.example.Infrastructure.Services.Realty.Sorting.*;

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
                    Map.entry(SortingMenuOptions.ByAllFields, "По всем полям"),
                    Map.entry(SortingMenuOptions.ByEvenCost, "По четной стоимости")
            )
    );

    @Override
    public void run() {
        print();

        var choice = readOption(SortingMenuOptions.class);

        RealtySortingStrategy sortingStrategy = switch (choice) {
            case ByAddress -> new ComparatorRealtySortingStrategy(
                    realtySorter,
                    new AddressSortingStrategy()
            );
            case ByArea -> new ComparatorRealtySortingStrategy(
                    realtySorter,
                    new AreaSortingStrategy()
            );
            case ByCost -> new ComparatorRealtySortingStrategy(
                    realtySorter,
                    new CostSortingStrategy()
            );
            case ByAllFields -> new ComparatorRealtySortingStrategy(
                    realtySorter,
                    new AllFieldsSortingStrategy()
            );
            case ByEvenCost -> new EvenCostRealtySortingStrategy(realtySorter);
        };

        var realty = realtyGetter.getRealty(null);

        var sortedRealty = sortingStrategy.sort(realty);

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
