package Infrastructure.Services.Realty;

import DataAccess.Data;
import DataAccess.Realty.RealtyDao;
import Domain.Contracts.Monads.Result;
import Domain.Contracts.Realty.RealtyUpdater;
import Domain.Contracts.Validators.Validator;
import Domain.Models.BusinessError;
import Domain.Models.RealtyDto;

import java.util.Collection;

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
}
