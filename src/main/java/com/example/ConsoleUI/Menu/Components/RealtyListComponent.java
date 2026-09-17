package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.RealtyDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

/**
 * Компонент отображения списка недвижимости.
 */
public class RealtyListComponent extends ConsoleDataComponent<Collection<RealtyDto>> {

    public RealtyListComponent(RealtyGetter realtyGetter){
        this.realtyGetter = realtyGetter;
    }

    private final RealtyGetter realtyGetter;

    @Override
    public void print(){
        var data = realtyGetter.getRealty(null);

        printData(data);
    }

    @Override
    public void print(Collection<RealtyDto> data) {
        printData(data);
    }

    private void printData(Collection<RealtyDto> data) {
        if (data == null){
            System.err.println("Не удалось получить данные");
            return;
        }

        if (data.isEmpty()){
            System.out.println("Список пуст");
            return;
        }

        var addressColumnWidth = data.stream().mapToInt(r -> formatAddress(r).length()).max().orElse(0);
        var areaColumnWidth = data.stream().mapToInt(r -> formatArea(r).length()).max().orElse(0);;
        var costColumnWidth = data.stream().mapToInt(r -> formatCost(r).length()).max().orElse(0);;

        for(var realty : data){
            var formattedText = FormatRealty(realty, addressColumnWidth, areaColumnWidth, costColumnWidth);
            System.out.println(formattedText);
        }
    }

    private String FormatRealty(RealtyDto realty, int addressColumnWidth, int areaColumnWidth, int costColumnWidth){
        var gap = 4;
        return String.format(
                "%-" + addressColumnWidth + "s%" + gap + "s%-" + areaColumnWidth + "s%" + gap + "s%-" + costColumnWidth + "s",
                formatAddress(realty),
                "",
                formatArea(realty),
                "",
                formatCost(realty)
        );
    }

    private String formatAddress(RealtyDto realty) {
        return "Адрес: " + realty.getAddress();
    }

    private String formatArea(RealtyDto realty) {
        var roundArea = Math.round(realty.getTotalArea() * 100) / 100.0;
        return "Площадь: " + roundArea + " м2";
    }

    private String formatCost(RealtyDto realty) {
        var roundCost = Math.round(new BigDecimal(String.valueOf(realty.getCost())).setScale(2, RoundingMode.HALF_UP).doubleValue() * 100) / 100.0;

        return "Стоимость: " + roundCost + " руб.";
    }
}
