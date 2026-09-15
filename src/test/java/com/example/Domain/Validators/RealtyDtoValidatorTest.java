package com.example.Domain.Validators;

import com.example.Domain.Contracts.Validators.Validator;
import com.example.Domain.Models.RealtyDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для {@link RealtyDtoValidator}
 */
class RealtyDtoValidatorTest {

    private static Validator<RealtyDto> validator;

    @BeforeAll
    static void setUp(){
        validator = new RealtyDtoValidator();
    }

    private static final String validAddress = "Россия, проспект Космонавтов, д. 52, Алтынжар, Астраханская Область, 30, 416196";
    private static final BigDecimal validCost = new BigDecimal(1000000);
    private static final double validArea = 100.0;


    @ParameterizedTest
    @MethodSource("validProviderFactory")
    void realtyValidator_checkValidRealty(RealtyDto realtyDto) {
        var actualResult = validator.validate(realtyDto);

        assertNull(actualResult);
    }

    @ParameterizedTest
    @MethodSource("invalidAddressProviderFactory")
    void realtyValidator_checkInvalidRealtyAddress(RealtyDto realtyDto) {
        var actualResult = validator.validate(realtyDto);

        assertNotNull(actualResult);
        assertNotNull(actualResult.errorMessage());
    }

    @ParameterizedTest
    @MethodSource("invalidCostProviderFactory")
    void realtyValidator_checkInvalidRealtyCost(RealtyDto realtyDto) {
        var actualResult = validator.validate(realtyDto);

        assertNotNull(actualResult);
        assertNotNull(actualResult.errorMessage());
    }

    @ParameterizedTest
    @MethodSource("invalidAreaProviderFactory")
    void realtyValidator_checkInvalidRealtyArea(RealtyDto realtyDto) {
        var actualResult = validator.validate(realtyDto);

        assertNotNull(actualResult);
        assertNotNull(actualResult.errorMessage());
    }

    static Stream<RealtyDto> validProviderFactory(){
        return Stream.of(
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(Double.MAX_VALUE)
                        .setCost(new BigDecimal(Double.MIN_VALUE))
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress("   Россия, г. Муром, Приозерная ул., д. 1 кв.177   ")
                        .setTotalArea(Double.MIN_VALUE)
                        .setCost(new BigDecimal(Long.MAX_VALUE))
                        .build()
        );
    }

    static Stream<RealtyDto> invalidAddressProviderFactory(){
        return Stream.of(
                RealtyDto.RealtyBuilder.create()
                        .setAddress("")
                        .setTotalArea(validArea)
                        .setCost(validCost)
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress("   ")
                        .setTotalArea(validArea)
                        .setCost(validCost)
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress(null)
                        .setTotalArea(validArea)
                        .setCost(validCost)
                        .build()
        );
    }

    static Stream<RealtyDto> invalidCostProviderFactory(){
        return Stream.of(
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(validArea)
                        .setCost(BigDecimal.ZERO)
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(validArea)
                        .setCost(new BigDecimal(Long.MIN_VALUE))
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(validArea)
                        .setCost(null)
                        .build()
        );
    }

    static Stream<RealtyDto> invalidAreaProviderFactory(){
        return Stream.of(
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(0)
                        .setCost(validCost)
                        .build(),
                RealtyDto.RealtyBuilder.create()
                        .setAddress(validAddress)
                        .setTotalArea(Long.MIN_VALUE)
                        .setCost(validCost)
                        .build()
        );
    }
}