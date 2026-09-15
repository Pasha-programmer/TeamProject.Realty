package com.example.DataAccess.Realty;

import java.math.BigDecimal;

/**
 * Модель информации о недвижимости
 */
public class RealtyDao {

    public RealtyDao(){
        this.address = null;
        this.cost = BigDecimal.ZERO;
        this.totalArea = 0;
    }

    public RealtyDao(String newAddress, BigDecimal newCost, double newTotalArea){
        address = newAddress;
        cost = newCost;
        totalArea = newTotalArea;
    }

    /**
     * Физический адрес недвижимости.
     */
    public String address;

    /**
     * Стоимость недвижимости.
     */
    public BigDecimal cost;

    /**
     * Общая площадь недвижимости в квадратных метрах.
     */
    public double totalArea;
}
