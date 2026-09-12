package com.example.Domain.Models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Модель информации о недвижимости
 */
public class RealtyDto {

    /**
     * Физический адрес недвижимости.
     */
    private String address;

    public String getAddress() { return address; }

    /**
     * Стоимость недвижимости.
     */
    private BigDecimal cost;

    public BigDecimal getCost() { return cost; }

    /**
     * Общая площадь недвижимости в квадратных метрах.
     */
    private double totalArea;

    public double getTotalArea() { return totalArea; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RealtyDto realtyDto2 = (RealtyDto) obj;
        return totalArea == realtyDto2.totalArea
                && Objects.equals(address, realtyDto2.address)
                && Objects.equals(cost, realtyDto2.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, cost, totalArea);
    }

    /**
     * Строитель модели информации о недвижимости.
     */
    public static class RealtyBuilder {

        public RealtyBuilder(){
            realtyDto = new RealtyDto();
        }

        public static RealtyBuilder create(){
            return new RealtyBuilder();
        }

        private final RealtyDto realtyDto;

        public RealtyBuilder setAddress(String address){
            realtyDto.address = address;
            return this;
        }

        public RealtyBuilder setCost(BigDecimal cost){
            realtyDto.cost = cost;
            return this;
        }

        public RealtyBuilder setTotalArea(double totalArea){
            realtyDto.totalArea = totalArea;
            return this;
        }

        public RealtyDto build(){
            return realtyDto;
        }
    }
}
