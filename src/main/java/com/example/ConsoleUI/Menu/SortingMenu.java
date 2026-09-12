package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Contracts.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Enums.SortingMenuOptions;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortingMenu extends ConsoleStageMenu {

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

    }

    @Override
    public void print() {
        System.out.println("\nМеню сортировки:");

        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + "." + value);
        });

        System.out.print("Выберите способ сортировки: ");
    }
}
