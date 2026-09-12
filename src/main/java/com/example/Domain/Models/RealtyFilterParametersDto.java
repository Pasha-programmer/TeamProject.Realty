package com.example.Domain.Models;

import java.math.BigDecimal;

/**
 * Модель информации о недвижимости
 */
public class RealtyFilterParametersDto {

    /**
     * Физический адрес недвижимости.
     */
    public String address;

    /**
     * Стоимость недвижимости - нижняя граница.
     */
    public BigDecimal fromCost;

    /**
     * Стоимость недвижимости - верхняя граница.
     */
    public BigDecimal toCost;

    /**
     * Общая площадь недвижимости в квадратных метрах - нижняя граница.
     */
    public Double fromTotalArea;

    /**
     * Общая площадь недвижимости в квадратных метрах - верхняя граница.
     */
    public Double toTotalArea;

    /**
     * Строитель модели информации о недвижимости.
     */
    public static class RealtyFilterParametersBuilder {

        public RealtyFilterParametersBuilder(){
            realtyFilterParametersDto = new RealtyFilterParametersDto();
        }

        public static RealtyFilterParametersBuilder create(){
            return new RealtyFilterParametersBuilder();
        }

        private final RealtyFilterParametersDto realtyFilterParametersDto;

        public RealtyFilterParametersBuilder setAddress(String address){
            realtyFilterParametersDto.address = address;
            return this;
        }

        public RealtyFilterParametersBuilder setFromCost(BigDecimal fromCost){
            realtyFilterParametersDto.fromCost = fromCost;
            return this;
        }

        public RealtyFilterParametersBuilder setToCost(BigDecimal toCost){
            realtyFilterParametersDto.toCost = toCost;
            return this;
        }

        public RealtyFilterParametersBuilder setFromTotalArea(double fromTotalArea){
            realtyFilterParametersDto.fromTotalArea = fromTotalArea;
            return this;
        }

        public RealtyFilterParametersBuilder setToTotalArea(double toTotalArea){
            realtyFilterParametersDto.toTotalArea = toTotalArea;
            return this;
        }


        public RealtyFilterParametersDto build(){
            return realtyFilterParametersDto;
        }
    }
}
