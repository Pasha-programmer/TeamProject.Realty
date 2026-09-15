package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.External.JsonParser;
import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyImporter;
import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Сервис, реализующий контракт {@link RealtyImporter} для импорта данных.
 */
public class RealtyImportFromJsonService implements RealtyImporter {
    private final Validator<RealtyDto> realtyDtoValidator;
    private final JsonParser<RealtyDto> jsonRealtyParser;

    public RealtyImportFromJsonService(Validator<RealtyDto> validator, JsonParser<RealtyDto> jsonRealtyParser) {
        realtyDtoValidator = validator;
        this.jsonRealtyParser = jsonRealtyParser;
    }

    @Override
    public Result<Boolean> importFromFile(Path filePath) {
        if (Files.notExists(filePath)) {
            return new Result<>(false, new BusinessError("Файл не существует"));
        }

        var file = filePath.toFile();

        var deseialazeResult = deserializeJsonFile(file);

        if (deseialazeResult.error() != null){
            return new Result<>(false, deseialazeResult.error());
        }

        var isSuccess = Data.addRealty(deseialazeResult.value());

        if (!isSuccess){
            return new Result<>(false, new BusinessError("Не удалось сохранить данные"));
        }

        return new Result<>(true);
    }

    /**
     * Десериализировать JSON файл
     * @param file Файл
     * @return Резултат с коллекцией {@link RealtyDao}
     */
    private Result<Collection<RealtyDao>> deserializeJsonFile(File file){
        try (Stream<RealtyDto> realtyDtoStream = jsonRealtyParser.streamTargets(file, RealtyDto.class)) {
            var realtyDaos = realtyDtoStream
                    .filter(realtyDto -> realtyDtoValidator.validate(realtyDto) == null)
                    .map(r -> {
                        var dao = new RealtyDao();
                        dao.address = r.getAddress();
                        dao.cost = r.getCost();
                        dao.totalArea = r.getTotalArea();
                        return dao;
                    }).toList();

            return new Result<>(realtyDaos);

        } catch (IllegalArgumentException e) {
            return new Result<>(null, new BusinessError(e.getMessage()));
        } catch (JsonParseException e) {
            return new Result<>(null, new BusinessError("Ошибка чтения JSON файла: файл поврежден или не валидный"));
        } catch (JsonProcessingException e) {
            return new Result<>(null, new BusinessError("Ошибка обработки JSON файла"));
        } catch (IOException e) {
            return new Result<>(null, new BusinessError("Что-то пошло не так"));
        }
    }
}
