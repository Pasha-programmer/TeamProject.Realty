package Domain.Contracts.Realty;

import Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Контракт получения информации о недвижимости.
 */
public interface RealtyGetter {

    /**
     * Получить коллекцию полной информации о недвижимости.
     * @param limit Ограничение количества получения моделей.
     * @return Коллекция моделей информации о недвижимости.
     */
    public Collection<RealtyDto> getRealty(Integer limit);
}
