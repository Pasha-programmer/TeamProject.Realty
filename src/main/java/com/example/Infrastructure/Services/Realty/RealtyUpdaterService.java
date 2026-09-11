package com.example.Infrastructure.Services.Realty;

import java.util.Collection;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Monads.Result;
import com.example.Domain.Contracts.Realty.RealtyUpdater;
import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;

/**
 * Сервис, реализующий контракт {@link RealtyUpdater} с валидацией данных.
 */
public class RealtyUpdaterService implements RealtyUpdater {

    public RealtyUpdaterService(Validator<RealtyDto> realtyDtoValidator){
        this.realtyDtoValidator = realtyDtoValidator;
    }

    private final Validator<RealtyDto> realtyDtoValidator;

    @Override
    public Result<Boolean> addRealty(Collection<RealtyDto> realtyDtos) {

        if (!realtyDtos.stream().allMatch(r -> realtyDtoValidator.validate(r) == null)){
            return new Result<>(false, new BusinessError("Не все записи валидны"));
        }

        var realtyDao = realtyDtos.stream().map(r -> {
            var dao = new RealtyDao();
            dao.address = r.getAddress();
            dao.cost = r.getCost();
            dao.totalArea = r.getTotalArea();
            return dao;
        }).toList();

        return new Result<>(Data.addRealty(realtyDao));
    }

    @Override
    public Result<Boolean> addRealty(RealtyDto realtyDto) {
        var validationResult = realtyDtoValidator.validate(realtyDto); 
        if(validationResult != null){
            return new Result<Boolean>(false, validationResult);
        }

        return new Result<Boolean>(Data.addRealty(new RealtyDao(realtyDto.getAddress(), 
                                                                realtyDto.getCost(), 
                                                                realtyDto.getTotalArea())));
    }
}
