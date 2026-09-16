package com.example.Infrastructure.Services.Realty;

import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Contracts.Realty.RealtyUploader;
import com.example.Domain.Models.BusinessError;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Сервис, реализующий контракт {@link RealtyUploader} по сохранению данных в файл
 */
public class RealtyUploaderService implements RealtyUploader {
    private final ObjectMapper mapper = new ObjectMapper();
    private final RealtyGetter realtyGetter;

    public RealtyUploaderService(RealtyGetter realtyGetter) {
        this.realtyGetter = realtyGetter;
    }

    @Override
    public Result<Boolean> saveToFile(Path filePath) {
        var data = realtyGetter.getRealty(Integer.MAX_VALUE);
        try {
            String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            Files.write(filePath, jsonStr.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return new Result<>(true);
        } catch (IOException e) {
            return new Result<>(false, new BusinessError("Что-то пошло не так: " + e.getMessage()));
        }
    }
}
