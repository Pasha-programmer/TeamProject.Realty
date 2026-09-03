package Infrastructure.Services.Realty;

import DataAccess.Data;
import Domain.Contracts.Realty.RealtyGetter;
import Domain.Models.RealtyDto;

import java.util.Collection;

public class RealtyGetterService implements RealtyGetter {

    @Override
    public Collection<RealtyDto> GetRealty(Integer limit) {
        var data = Data.getRealty();

        return ApplyFilters(data, limit);
    }

    /**
     * Применить фильтрацию.
     * @param realty Коллекция фильтруемых недвижимостей.
     * @param limit Ограничение на количество.
     * @return Фильтрованная коллекция недвижимостей.
     */
    private Collection<RealtyDto> ApplyFilters(Collection<RealtyDto> realty, Integer limit){
        var realtyStream = realty.stream();

        if (limit != null){
            realtyStream = realtyStream.limit(limit);
        }

        return realtyStream.toList();
    }
}
