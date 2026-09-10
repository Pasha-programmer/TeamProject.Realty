package com.example;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.MainMenu;
import com.example.Infrastructure.Services.Realty.RealtyGetterService;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        try(var scanner = new Scanner(System.in)){
            ConsoleStageMenu mainMenu = new MainMenu(
                    scanner,
                    new RealtyGetterService()
            );
            mainMenu.run();
        }
    }
}
