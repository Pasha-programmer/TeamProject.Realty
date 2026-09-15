package com.example.ConsoleUI.Menu;

import com.example.ConsoleUI.Menu.Components.InputIntegerComponent;
import com.example.ConsoleUI.Menu.Components.Properties.InputIntegerComponentProperties;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.Domain.Contracts.Realty.RealtyGenerator;
import com.example.Domain.Contracts.Realty.RealtyUpdater;

import java.util.Scanner;

/**
 * Меню генерации записей.
 */
public class GenerationMenu extends ConsoleStageMenu {

    private final InputComponent<Integer> integerInputComponent;
    private final RealtyUpdater updaterService;
    private final RealtyGenerator realtyGenerator;

    public GenerationMenu(Scanner scanner, RealtyUpdater updaterService, RealtyGenerator realtyGenerator){
        super(scanner);
        integerInputComponent = new InputIntegerComponent(new InputIntegerComponentProperties(scanner, 1, 1000));
        this.updaterService = updaterService;
        this.realtyGenerator = realtyGenerator;
    }

    @Override
    public void print() {
        System.out.println("Введите количество записей: ");
    }

    @Override
    public void run() {

        while(isRun()){

            print();

            var count = integerInputComponent.read();

            var realtyDtos = realtyGenerator.generate(count);

            updaterService.addRealty(realtyDtos);
        }
    }

}


