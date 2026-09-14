package com.example.Infrastructure.Services.Realty.Sorting;

import com.example.Domain.Contracts.Realty.RealtySorter;
import com.example.Domain.Models.RealtyDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для {@link MergeSortRealtySorter}
 */
class MergeSortRealtySorterTest {

    private static RealtySorter sorter;
    private static Comparator<RealtyDto> byCost;

    @BeforeAll
    static void setUp() {
        sorter = new MergeSortRealtySorter();
        byCost = Comparator.comparing(RealtyDto::getCost);
    }

    /**
     * Вспомогательный метод для создания RealtyDto с заданной ценой.
     */
    private RealtyDto createRealtyDto(BigDecimal cost) {
        return RealtyDto.RealtyBuilder.create()
                .setCost(cost)
                .build();
    }

    @Test
    void sort_shouldSortCollectionByComparator() {
        var input = List.of(
                createRealtyDto(new BigDecimal(300)),
                createRealtyDto(new BigDecimal(100)),
                createRealtyDto(new BigDecimal(200))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(3, resultList.size());
        assertEquals(new BigDecimal(100), resultList.get(0).getCost());
        assertEquals(new BigDecimal(200), resultList.get(1).getCost());
        assertEquals(new BigDecimal(300), resultList.get(2).getCost());
    }

    @Test
    void sort_shouldReturnEmptyCollection_whenInputIsEmpty() {
        var input = new ArrayList<RealtyDto>(0);

        var result = sorter.sort(input, byCost);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void sort_shouldReturnSingleElement_whenInputHasOneElement() {
        var single = createRealtyDto(new BigDecimal(42));
        var input = List.of(single);

        var result = sorter.sort(input, byCost);

        assertEquals(1, result.size());
        assertEquals(new BigDecimal(42), result.iterator().next().getCost());
    }

    @Test
    void sort_shouldKeepOrder_whenInputAlreadySorted() {
        var input = List.of(
                createRealtyDto(new BigDecimal(1)),
                createRealtyDto(new BigDecimal(2)),
                createRealtyDto(new BigDecimal(3)),
                createRealtyDto(new BigDecimal(4))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(new BigDecimal(1), resultList.get(0).getCost());
        assertEquals(new BigDecimal(2), resultList.get(1).getCost());
        assertEquals(new BigDecimal(3), resultList.get(2).getCost());
        assertEquals(new BigDecimal(4), resultList.get(3).getCost());
    }

    @Test
    void sort_shouldReverseOrder_whenInputIsReversed() {
        var input = List.of(
                createRealtyDto(new BigDecimal(4)),
                createRealtyDto(new BigDecimal(3)),
                createRealtyDto(new BigDecimal(2)),
                createRealtyDto(new BigDecimal(1))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(new BigDecimal(1), resultList.get(0).getCost());
        assertEquals(new BigDecimal(2), resultList.get(1).getCost());
        assertEquals(new BigDecimal(3), resultList.get(2).getCost());
        assertEquals(new BigDecimal(4), resultList.get(3).getCost());
    }

    @Test
    void sort_shouldHandleDuplicates() {
        var input = List.of(
                createRealtyDto(new BigDecimal(5)),
                createRealtyDto(new BigDecimal(2)),
                createRealtyDto(new BigDecimal(5)),
                createRealtyDto(new BigDecimal(2)),
                createRealtyDto(new BigDecimal(5))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(5, resultList.size());
        assertEquals(new BigDecimal(2), resultList.get(0).getCost());
        assertEquals(new BigDecimal(2), resultList.get(1).getCost());
        assertEquals(new BigDecimal(5), resultList.get(2).getCost());
        assertEquals(new BigDecimal(5), resultList.get(3).getCost());
        assertEquals(new BigDecimal(5), resultList.get(4).getCost());
    }

    @Test
    void sort_shouldHandleEvenNumberOfElements() {
        var input = List.of(
                createRealtyDto(new BigDecimal(10)),
                createRealtyDto(new BigDecimal(30)),
                createRealtyDto(new BigDecimal(20)),
                createRealtyDto(new BigDecimal(40))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(new BigDecimal(10), resultList.get(0).getCost());
        assertEquals(new BigDecimal(20), resultList.get(1).getCost());
        assertEquals(new BigDecimal(30), resultList.get(2).getCost());
        assertEquals(new BigDecimal(40), resultList.get(3).getCost());
    }

    @Test
    void sort_shouldHandleOddNumberOfElements() {
        var input = List.of(
                createRealtyDto(new BigDecimal(30)),
                createRealtyDto(new BigDecimal(10)),
                createRealtyDto(new BigDecimal(50)),
                createRealtyDto(new BigDecimal(20)),
                createRealtyDto(new BigDecimal(40))
        );

        var result = sorter.sort(input, byCost);
        var resultList = new ArrayList<>(result);

        assertEquals(new BigDecimal(10), resultList.get(0).getCost());
        assertEquals(new BigDecimal(20), resultList.get(1).getCost());
        assertEquals(new BigDecimal(30), resultList.get(2).getCost());
        assertEquals(new BigDecimal(40), resultList.get(3).getCost());
        assertEquals(new BigDecimal(50), resultList.get(4).getCost());
    }

    @Test
    void sort_shouldBeStable_whenComparatorReturnsZero() {
        // Все элементы равны по компаратору, порядок должен сохраниться
        var first = createRealtyDto(new BigDecimal(1));
        var second = createRealtyDto(new BigDecimal(1));
        var third = createRealtyDto(new BigDecimal(1));

        var input = List.of(first, second, third);

        var result = sorter.sort(input, byCost);
        List<RealtyDto> resultList = new ArrayList<>(result);

        assertSame(first, resultList.get(0));
        assertSame(second, resultList.get(1));
        assertSame(third, resultList.get(2));
    }

    @Test
    void sort_shouldNotModifyInputCollection() {
        var input = new ArrayList<>(List.of(
                createRealtyDto(new BigDecimal(3)),
                createRealtyDto(new BigDecimal(1)),
                createRealtyDto(new BigDecimal(2))
        ));
        var copy = new ArrayList<>(input);

        sorter.sort(input, byCost);

        assertEquals(copy.size(), input.size());
        for (int i = 0; i < copy.size(); i++) {
            assertSame(copy.get(i), input.get(i));
        }
    }

    @Test
    void sort_shouldUseCustomComparator() {
        var byPriceDesc = Comparator.comparing(RealtyDto::getCost).reversed();

        var input = List.of(
                createRealtyDto(new BigDecimal(1)),
                createRealtyDto(new BigDecimal(3)),
                createRealtyDto(new BigDecimal(2))
        );

        var result = sorter.sort(input, byPriceDesc);
        var resultList = new ArrayList<>(result);

        assertEquals(new BigDecimal(3), resultList.get(0).getCost());
        assertEquals(new BigDecimal(2), resultList.get(1).getCost());
        assertEquals(new BigDecimal(1), resultList.get(2).getCost());
    }

    @Test
    void sort_shouldBreakOnNullInput() {
        assertThrows(NullPointerException.class, () -> sorter.sort(null, byCost));
    }

    @Test
    void sort_shouldBreakOnNullComparator() {
        assertThrows(NullPointerException.class, () -> sorter.sort(new ArrayList<>(), null));
    }
}