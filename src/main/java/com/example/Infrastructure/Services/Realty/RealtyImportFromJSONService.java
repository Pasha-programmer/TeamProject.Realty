package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyUploader;
import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Validators.RealtyDtoValidator;
import com.example.Infrastructure.Services.External.JacksonStreamService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class RealtyImportFromJSONService implements RealtyUploader {
    private final Scanner scanner;
    private final Validator<RealtyDto> realtyDtoValidator = new RealtyDtoValidator();
    private final JacksonStreamService<RealtyDto> jacksonStreamService = new JacksonStreamService<>();

    public RealtyImportFromJSONService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Result<Boolean> uploadRealty() {
        String pathStr = scanner.nextLine().trim();
        if (pathStr.isEmpty()) {
            System.out.println("Вы не ввели путь к файлу");
            return new Result<>(false);
        }

        File theFile = Path.of(pathStr).toFile();

        try (Stream<RealtyDto> realtyDtoStream = jacksonStreamService.streamRealtyJson(theFile, RealtyDto.class)) {
            var dataFromFile = realtyDtoStream
                    .filter(realtyDto -> realtyDtoValidator.validate(realtyDto) == null)
                    .map(r -> {
                        var dao = new RealtyDao();
                        dao.address = r.getAddress();
                        dao.cost = r.getCost();
                        dao.totalArea = r.getTotalArea();
                        return dao;
                    }).toList();

            System.out.println("\nУспешно обработали JSON документ. Количество добавленных объектов: " + dataFromFile.size());

            return new Result<>(Data.addRealty(dataFromFile));
        } catch (IOException e) {
            System.err.println("Error Input/Out while processing file: " + e.getMessage());
            return new Result<>(false);
        }
    }
}
