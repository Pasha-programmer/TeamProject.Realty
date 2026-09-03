package DataAccess;

import Domain.Models.RealtyDto;

import java.util.ArrayList;
import java.util.Collection;

public final class Data {

    private static final ArrayList<RealtyDto> realty = new ArrayList<>();

    /**
     * Получить коллекцию данных о недвижимости.
     * @return Коллекция данных о недвижимости.
     */
    public static Collection<RealtyDto> getRealty(){
        return realty;
    }

    /**
     * Добавить коллекцию информации о недвижимостях.
     * @param realtyDtos Коллекция информации о недвижимостях.
     * @return true - если добавление успешно, иначе false.
     */
    public static boolean addRealty(Collection<RealtyDto> realtyDtos){
        return realty.addAll(realtyDtos);
    }
}
