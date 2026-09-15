package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleComponent;
import com.example.Domain.Validators.RealtyDtoValidator;
import com.example.Infrastructure.Services.External.JacksonStreamService;
import com.example.Infrastructure.Services.Realty.RealtyImportFromJsonService;

import java.util.Scanner;

/**
 * Компонент импорта данных из JSON файла.
 */
public class RealtyImportFromJsonComponent extends ConsoleComponent {
    private final RealtyImportFromJsonService realtyImportFromJsonService;
    private final Scanner scanner;

    public RealtyImportFromJsonComponent(Scanner scanner) {
        this.scanner = scanner;
        realtyImportFromJsonService = new RealtyImportFromJsonService(new RealtyDtoValidator(), new JacksonStreamService<>());
    }

    @Override
    public void print() {}

    public void uploadData() {
        String filePath = scanner.nextLine().trim();
        realtyImportFromJsonService.uploadRealty(filePath);
    }
}
