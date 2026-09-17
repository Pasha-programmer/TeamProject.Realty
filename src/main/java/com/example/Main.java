package com.example;

import java.util.Scanner;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.MainMenu;
import com.example.Domain.Validators.RealtyDtoValidator;
import com.example.Infrastructure.Services.External.JacksonJsonParserService;
import com.example.Infrastructure.Services.Realty.*;
import com.example.Infrastructure.Services.Realty.Sorting.MergeSortRealtySorter;

public class Main {
    static void main(String[] args) {

        var realtyUpdaterService = new RealtyUpdaterService(new RealtyDtoValidator());

        try(var scanner = new Scanner(System.in)){
            ConsoleStageMenu mainMenu = new MainMenu(
                    scanner,
                    new RealtyGetterService(),
                    new MergeSortRealtySorter(),
                    realtyUpdaterService,
                    new RealtyGeneratorService(),
                    new RealtyImportFromJsonService(new RealtyDtoValidator(), new JacksonJsonParserService<>(), realtyUpdaterService),
                    new RealtyExportToJsonService()
            );
            mainMenu.run();
        }
    }
}
