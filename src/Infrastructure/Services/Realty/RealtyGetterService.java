package Infrastructure.Services.Realty;

import DataAccess.Data;
import DataAccess.Realty.RealtyDao;
import Domain.Contracts.Realty.RealtyGetter;
import Domain.Models.RealtyDto;

import java.util.Collection;

public class RealtyGetterService implements RealtyGetter {

    @Override
    public Collection<RealtyDto> getRealty(Integer limit) {
        var data = Data.getRealty();

        var filteredRealty = ApplyFilters(data, limit);

        return filteredRealty.stream().map(r -> RealtyDto.RealtyBuilder.create()
                .setAddress(r.address)
                .setCost(r.cost)
                .setTotalArea(r.totalArea)
                .build())
            .toList();
    }

    /**
     * Применить фильтрацию.
     * @param realty Коллекция фильтруемых недвижимостей.
     * @param limit Ограничение на количество.
     * @return Фильтрованная коллекция недвижимостей.
     */
    private Collection<RealtyDao> ApplyFilters(Collection<RealtyDao> realty, Integer limit){
        var realtyStream = realty.stream();

        if (limit != null){
            realtyStream = realtyStream.limit(limit);
        }

        return realtyStream.toList();
    }
}
