package DataAccess;

import DataAccess.Realty.RealtyDao;

import java.util.ArrayList;
import java.util.Collection;

public final class Data {

    private static final ArrayList<RealtyDao> realty = new ArrayList<>();

    /**
     * Получить коллекцию данных о недвижимости.
     * @return Коллекция данных о недвижимости.
     */
    public static Collection<RealtyDao> getRealty(){
        return realty;
    }

    /**
     * Добавить коллекцию информации о недвижимостях.
     * @param realtyDaos Коллекция информации о недвижимостях.
     * @return true - если добавление успешно, иначе false.
     */
    public static boolean addRealty(Collection<RealtyDao> realtyDaos){
        return realty.addAll(realtyDaos);
    }
}
