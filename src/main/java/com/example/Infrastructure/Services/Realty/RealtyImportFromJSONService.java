package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.External.JacksonParser;
import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyUploader;
import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.RealtyDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Сервис, реализующий контракт {@link RealtyUploader} для импорта данных.
 */
public class RealtyImportFromJsonService implements RealtyUploader {
    private final Validator<RealtyDto> realtyDtoValidator;
    private final JacksonParser<RealtyDto> jacksonStreamService;

    public RealtyImportFromJsonService(Validator<RealtyDto> validator, JacksonParser<RealtyDto> jacksonParser) {
        realtyDtoValidator = validator;
        jacksonStreamService = jacksonParser;
    }

    @Override
    public Result<Boolean> uploadRealty(String filePath) {
        Path pathToFile = Path.of(filePath);
        if (filePath.isEmpty() || Files.notExists(pathToFile)) {
            System.err.println("Введите корректный путь до существующего файла");
            return new Result<>(false);
        }

        File file = pathToFile.toFile();

        try (Stream<RealtyDto> realtyDtoStream = jacksonStreamService.streamRealtyJson(file, RealtyDto.class)) {
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
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return new Result<>(false);
        } catch (JsonParseException e) {
            System.err.println("Ошибка чтения JSON файла: файл поврежден или не валидный.");
            return new Result<>(false);
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка обработки JSON файла");
            return new Result<>(false);
        } catch (IOException e) {
            System.err.println("Файл не найден");
            return new Result<>(false);
        }
    }
}
