package com.example;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.MainMenu;
import com.example.Infrastructure.Services.Realty.RealtyGetterService;

public class Main {
    public static void main(String[] args) {

        ConsoleStageMenu mainMenu = new MainMenu(
                new RealtyGetterService()
        );
        mainMenu.run();
    }
}
