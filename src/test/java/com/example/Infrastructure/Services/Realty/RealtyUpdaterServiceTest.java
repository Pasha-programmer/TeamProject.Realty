package com.example.Infrastructure.Services.Realty;

import com.example.DataAccess.Data;
import com.example.DataAccess.Realty.RealtyDao;
import com.example.Domain.Contracts.Realty.RealtyUpdater;
import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.RealtyDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для {@link RealtyUpdaterService}
 */
class RealtyUpdaterServiceTest {

    private static RealtyUpdater service;

    @BeforeAll
    static void setUp(){
        Validator<RealtyDto> realtyDtoValidator = Mockito.mock(Validator.class);
        service = new RealtyUpdaterService(realtyDtoValidator);
    }

    @Test
    void addRealty_checkSaveResponse() {
        var testRealtyDtos = new ArrayList<RealtyDto>(3);
        testRealtyDtos.add(createRealtyDto(null, 100.5, "Москва, Ленина, 456"));
        testRealtyDtos.add(createRealtyDto(new BigDecimal("123456.56"), 0, "Ижевск, Удмуртская, 1"));
        testRealtyDtos.add(createRealtyDto(new BigDecimal(8484), -100.5, null));

        var result = service.addRealty(testRealtyDtos);

        assertTrue(result.value());
    }

    @Test
    void addRealty_checkFactSave() {
        var testRealtyDtos = new ArrayList<RealtyDto>(3);
        testRealtyDtos.add(createRealtyDto(
                BigDecimal.ZERO,
                Double.MAX_VALUE,
                "Россия, проспект Космонавтов, д. 52, Алтынжар, Астраханская Область, 30, 416196"));

        service.addRealty(testRealtyDtos);

        var actualRealty = Data.getRealty();

        assertTrue(actualRealty.stream().anyMatch(r -> equals(testRealtyDtos.getFirst(), r)));
    }

    private RealtyDto createRealtyDto(BigDecimal cost, double area, String address){
        return RealtyDto.RealtyBuilder.create()
                .setAddress(address)
                .setCost(cost)
                .setTotalArea(area)
                .build();
    }

    private boolean equals(RealtyDto dto, RealtyDao dao){
        return dto.getAddress().equals(dao.address)
                && dto.getTotalArea() == dao.totalArea
                && dto.getCost().equals(dao.cost);
    }
}