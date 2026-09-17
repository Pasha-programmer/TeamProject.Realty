package com.example.Infrastructure.Services.Realty;

import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtyExporter;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

/**
 * Сервис, реализующий контракт {@link RealtyExporter} по сохранению данных в json файл
 */
public class RealtyExportToJsonService implements RealtyExporter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Result<Boolean> saveToFile(Collection<RealtyDto> data, Path filePath) {
        try {
            var jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            Files.write(filePath, jsonStr.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return new Result<>(true);
        } catch(JsonProcessingException e){
            return new Result<>(false, new BusinessError("Не удалось преобразовать данные."));
        }
        catch (IOException e) {
            return new Result<>(false, new BusinessError("Что-то пошло не так."));
        }
    }
}
