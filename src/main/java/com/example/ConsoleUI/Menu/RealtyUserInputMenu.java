package com.example.ConsoleUI.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

import com.example.ConsoleUI.Menu.Components.InputDoubleComponent;
import com.example.ConsoleUI.Menu.Components.InputMoneyComponent;
import com.example.ConsoleUI.Menu.Components.InputStringComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.Domain.Contracts.Realty.RealtyUpdater;
import com.example.Domain.Models.RealtyDto;

/**
 * Этап ручного ввода данных недвижимости
 */
public class RealtyUserInputMenu extends ConsoleStageMenu {

    private final RealtyUpdater realtySetter;
    private final InputComponent<String> inputStringComponent;
    private final InputComponent<BigDecimal> inputMoneyComponent;
    private final InputComponent<Double> inputDoubleComponent;

    public RealtyUserInputMenu(Scanner scanner, RealtyUpdater realtySetter){
        super(scanner);
        this.realtySetter = realtySetter;
        this.inputStringComponent = new InputStringComponent(scanner);
        this.inputMoneyComponent = new InputMoneyComponent(scanner);
        this.inputDoubleComponent = new InputDoubleComponent(scanner);
    }

    @Override
    public void run() {
        System.out.println("Введите адрес недвижимости. ");
        var address = inputStringComponent.read();
        System.out.println("Введите стоимость недвижимости. ");
        var cost = inputMoneyComponent.read();
        System.out.println("Введите размер площади. ");
        var size = inputDoubleComponent.read();
        var newRealty = RealtyDto.RealtyBuilder.create()
                                .setAddress(address)
                                .setCost(cost)
                                .setTotalArea(size)
                                .build();

        var inputResult = realtySetter.addRealty(newRealty); 
        if(!inputResult.value()){
            System.out.println("Произошла ошибка при добавлении значения в коллекцию.");
            System.out.print(inputResult.error());
        }
    }

    @Override
    public void print() {
        System.ou
    }
}
