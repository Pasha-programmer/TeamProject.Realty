package com.example.ConsoleUI.Menu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.Domain.Models.RealtyDto;
import  com.example.Infrastructure.Services.Realty.GenerationRandomHouse;
import com.example.Infrastructure.Services.Realty.RealtyUpdaterService;

import java.util.ArrayList;
import java.util.Scanner;

// созддаёт меню генерации

public class GenerationMenu extends ConsoleStageMenu {

    @Override
    public void run() {

        while(isRun()){

            print();
            int num;

            while (true) {
                try {

                    num = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException e) {
                    System.err.println("Введите корректное целое число");
                    continue;
                }
                break;
            }

            ArrayList<RealtyDto> realtyDtos = new ArrayList<>();

            for (int i = 0; i < num; i++) {
                var obj = GenerationRandomHouse.generate();
                realtyDtos.add(obj);
            }
            updaterService.addRealty(realtyDtos);

        }
    }

    @Override
    public void print() {
        System.out.println("Введите количество записей: ");
    }

    private RealtyUpdaterService updaterService;
    public GenerationMenu (Scanner scanner, RealtyUpdaterService updaterService) {

        super(scanner);
        this.updaterService = updaterService;

    }
}


