package Domain.Models;

import java.math.BigDecimal;

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
