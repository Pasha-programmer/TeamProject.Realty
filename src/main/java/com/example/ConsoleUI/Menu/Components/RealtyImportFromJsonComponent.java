package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleComponent;
import com.example.Infrastructure.Services.Realty.RealtyImportFromJSONService;

import java.util.Scanner;

public class RealtyImportFromJsonComponent extends ConsoleComponent {
    private final RealtyImportFromJSONService realtyImportFromJSONService;

    public RealtyImportFromJsonComponent(Scanner scanner) {
        realtyImportFromJSONService = new RealtyImportFromJSONService(scanner);
    }

    @Override
    public void print() {}

    public void uploadData() {
        realtyImportFromJSONService.uploadRealty();
    }
}
