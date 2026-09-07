package Domain.Contracts.Realty;

import Domain.Contracts.Monads.Result;
import Domain.Models.RealtyDto;

import java.util.Collection;

/**
 * Контракт обновления информации о недвижимости.
 */
public interface RealtyUpdater {

    /**
     * Добавить модели информации о недвижимости.
     * @param realtyDtos модели информации о недвижимости.
     * @return true - если добавление успешно, иначе false.
     */
    public Result<Boolean> addRealty(Collection<RealtyDto> realtyDtos);
}
