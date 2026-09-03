package Domain.Models;

import java.math.BigDecimal;

/**
 * Модель информации о недвижимости
 */
public class RealtyDto {

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
