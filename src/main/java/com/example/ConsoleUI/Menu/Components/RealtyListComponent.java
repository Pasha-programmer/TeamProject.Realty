package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleDataComponent;
import com.example.Domain.Contracts.Realty.RealtyGetter;
import com.example.Domain.Models.RealtyDto;

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

        for(var realty : data){
            System.out.println(FormatRealty(realty));
        }
    }

    private String FormatRealty(RealtyDto realty){
        return "Адрес: " + realty.getAddress()
                + "\tПлощадь: " + realty.getTotalArea() + "м2"
                + "\tСтоимость: " + realty.getCost() + "руб.";
    }
}
