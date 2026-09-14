package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Models.RealtyFilterParametersDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для {@link RealtyGetterService}
 */
class RealtyGetterServiceTest {

    private static RealtyGetter service;

    private static final List<RealtyDao> testRealtyDaos = new ArrayList<>(5);

    @BeforeAll
    static void setUp(){
        service = new RealtyGetterService();

        testRealtyDaos.add(createRealtyDao(new BigDecimal(100000), 100.5, "Москва, Ленина, 456"));
        testRealtyDaos.add(createRealtyDao(new BigDecimal("123456.56"), 123.45, "Ижевск, Удмуртская, 1"));
        testRealtyDaos.add(createRealtyDao(new BigDecimal(8484), 100.5, "Уфа, Советская - 123"));
        testRealtyDaos.add(createRealtyDao(new BigDecimal(63737523), 1528, "Любой город, любая улица, любой дом"));
        testRealtyDaos.add(createRealtyDao(new BigDecimal(999999999), 956278.456, "ыфыап ыв ыав ываарео1455"));

        Data.addRealty(testRealtyDaos);
    }

    private static RealtyDao createRealtyDao(BigDecimal cost, double area, String address){
        var item = new RealtyDao();
        item.cost = cost;
        item.totalArea = area;
        item.address = address;
        return item;
    }

    @Test
    void getRealty_checkCount() {
        var expectedCount = 3;
        var actualItems = service.getRealty(expectedCount);

        assertEquals(expectedCount, actualItems.size());
    }

    @Test
    void getRealty_checkFillModels() {
        var actualItems = service.getRealty(null);

        assertTrue(actualItems.stream().allMatch(x -> x.getAddress() != null));
        assertTrue(actualItems.stream().allMatch(x -> x.getCost() != null));
        assertTrue(actualItems.stream().allMatch(x -> !Double.isNaN(x.getTotalArea()) && !Double.isInfinite(x.getTotalArea())));
    }

    @Test
    void getRealtyCount_checkCountByFilter_address() {
        var filters = RealtyFilterParametersDto.RealtyFilterParametersBuilder.create()
                .setAddress(testRealtyDaos.get(0).address)
                .build();

        var actualCount = service.getRealtyCount(filters);

        var expectedCount = (int) testRealtyDaos.stream()
                .filter(r -> r.address.equals(filters.address))
                .count();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void getRealtyCount_checkCountByFilter_cost() {
        var filters = RealtyFilterParametersDto.RealtyFilterParametersBuilder.create()
                .setFromCost(testRealtyDaos.get(2).cost)
                .setToCost(testRealtyDaos.get(0).cost)
                .build();

        var actualCount = service.getRealtyCount(filters);

        var expectedCount = (int) testRealtyDaos.stream()
                .filter(r -> r.cost.compareTo(filters.fromCost) >=0 && r.cost.compareTo(filters.toCost) <= 0)
                .count();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void getRealtyCount_checkCountByFilter_area() {
        var filters = RealtyFilterParametersDto.RealtyFilterParametersBuilder.create()
                .setFromTotalArea(testRealtyDaos.get(0).totalArea)
                .setToTotalArea(testRealtyDaos.get(3).totalArea)
                .build();

        var actualCount = service.getRealtyCount(filters);

        var expectedCount = (int) testRealtyDaos.stream()
                .filter(r -> r.totalArea >= filters.fromTotalArea && r.totalArea <= filters.toTotalArea)
                .count();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void getRealtyCount_checkCountByTarget() {
        var target = RealtyDto.RealtyBuilder.create()
                .setAddress("Ижевск, Удмуртская, 1")
                .setCost(new BigDecimal(123456.56))
                .setTotalArea(123.45)
                .build();

        var actualCount = service.getRealtyCount(target);

        var expectedCount = (int) testRealtyDaos.stream()
                .filter(r -> r.address.equals(target.getAddress())
                        && r.cost.equals(target.getCost())
                        && r.totalArea == target.getTotalArea())
                .count();

        assertEquals(expectedCount, actualCount);
    }
}