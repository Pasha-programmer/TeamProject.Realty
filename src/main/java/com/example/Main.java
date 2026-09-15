package com.example;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.MainMenu;
import com.example.Domain.Validators.RealtyDtoValidator;
import com.example.Infrastructure.Services.Realty.RealtyGeneratorService;
import com.example.Infrastructure.Services.Realty.RealtyGetterService;
import com.example.Infrastructure.Services.Realty.RealtyUpdaterService;
import com.example.Infrastructure.Services.Realty.Sorting.MergeSortRealtySorter;

public class Main {
    static void main(String[] args) {
        try(var scanner = new Scanner(System.in)){
            ConsoleStageMenu mainMenu = new MainMenu(
                    scanner,
                    new RealtyGetterService(),
                    new MergeSortRealtySorter(),
                    new RealtyUpdaterService(new RealtyDtoValidator()),
                    new RealtyGeneratorService()
            );
            mainMenu.run();
        }
    }
}
