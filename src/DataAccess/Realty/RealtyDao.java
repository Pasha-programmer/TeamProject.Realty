package DataAccess.Realty;

import java.math.BigDecimal;

/**
 * Модель информации о недвижимости
 */
public class RealtyDao {

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
