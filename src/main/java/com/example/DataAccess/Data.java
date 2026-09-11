package com.example.DataAccess;

import java.util.ArrayList;
import java.util.Collection;

import com.example.DataAccess.Realty.RealtyDao;

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

    /**
     * Добавить информацию о недвижимости.
     * @param realtyDao информация о недвижимости.
     * @return true - если добавление успешно, иначе false.
     */
    public static boolean addRealty(RealtyDao realtyDao){
        return realty.add(realtyDao);
    }
}
