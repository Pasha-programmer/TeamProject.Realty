package com.example.ConsoleUI.Menu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.Domain.Models.RealtyDto;
import com.example.Infrastructure.Services.Realty.GenerationRealty;
import com.example.Infrastructure.Services.Realty.RealtyUpdaterService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Меню генерации записей.
 */
public class GenerationMenu extends ConsoleStageMenu {

    @Override
    public void print() {
        System.out.println("Введите количество записей: ");
    }

    @Override
    public void run() {

        while(isRun()){

            print();

            int num;

            while (true) {
                try {

                    num = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException e) {
                    System.err.println("Некорректное целое число");
                    continue;
                }
                break;
            }

            ArrayList<RealtyDto> realtyDtos = new ArrayList<>();

            for (int i = 0; i < num; i++) {
                var obj = GenerationRealty.generate();
                realtyDtos.add(obj);
            }
            updaterService.addRealty(realtyDtos);

        }
    }

    private RealtyUpdaterService updaterService;
    public GenerationMenu (Scanner scanner, RealtyUpdaterService updaterService) {

        super(scanner);
        this.updaterService = updaterService;

    }
}


