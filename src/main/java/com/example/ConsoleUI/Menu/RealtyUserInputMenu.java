package com.example.ConsoleUI.Menu;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Function;

import com.example.ConsoleUI.Menu.Components.InputDoubleComponent;
import com.example.ConsoleUI.Menu.Components.InputMoneyComponent;
import com.example.ConsoleUI.Menu.Components.InputStringComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import com.example.ConsoleUI.Menu.Contracts.Models.Common.MenuResult;
import com.example.Domain.Contracts.Realty.RealtyUpdater;
import com.example.Domain.Models.BusinessError;
import com.example.Domain.Models.RealtyDto;
import com.example.Domain.Validators.AddressValidator;
import com.example.Domain.Validators.CostValidator;
import com.example.Domain.Validators.TotalAreaValidator;

/**
 * Этап ручного ввода данных недвижимости
 */
public class RealtyUserInputMenu extends ConsoleStageMenu {

    private final RealtyUpdater realtySetter;
    private final InputComponent<String> inputStringComponent;
    private final InputComponent<BigDecimal> inputMoneyComponent;
    private final InputComponent<Double> inputDoubleComponent;
    private final AddressValidator addressValidator;
    private final CostValidator costValidator;
    private final TotalAreaValidator totalAreaValidator;

    public RealtyUserInputMenu(Scanner scanner, RealtyUpdater realtySetter){
        super(scanner);
        this.realtySetter = realtySetter;
        this.inputStringComponent = new InputStringComponent(scanner);
        this.inputMoneyComponent = new InputMoneyComponent(scanner);
        this.inputDoubleComponent = new InputDoubleComponent(scanner);
        this.addressValidator = new AddressValidator();
        this.costValidator = new CostValidator();
        this.totalAreaValidator = new TotalAreaValidator();
    }

    @Override
    public void run() {
        System.out.println("Введите адрес недвижимости.");
        var address = readValidated(inputStringComponent, addressValidator::validate);

        System.out.println("Введите размер площади.");
        var size = readValidated(inputDoubleComponent, totalAreaValidator::validate);

        System.out.println("Введите стоимость недвижимости.");
        var cost = readValidated(inputMoneyComponent, costValidator::validate);

        var newRealty = RealtyDto.RealtyBuilder.create()
                                .setAddress(address)
                                .setCost(cost)
                                .setTotalArea(size)
                                .build();

        var inputResult = realtySetter.addRealty(newRealty);
        if (!inputResult.value()) {
            var error = inputResult.error();
            var message = error != null ? error.errorMessage() : "Неизвестная ошибка.";
            processResult(MenuResult.stayInMenu("Не удалось добавить запись: " + message));
            return;
        }

        processResult(MenuResult.stayInMenu("Запись успешно добавлена."));
    }

    /**
     * Считать значение и проверить его бизнес-правилами.
     * При ошибке валидации запрашивается только это поле, остальные введенные значения сохраняются.
     * @param component компонент ввода (проверяет формат).
     * @param validator бизнес-проверка конкретного поля.
     * @return корректное значение.
     */
    private <T> T readValidated(InputComponent<T> component, Function<T, BusinessError> validator) {
        while (true) {
            var value = component.read();

            var error = validator.apply(value);
            if (error == null) {
                return value;
            }

            System.err.println(error.errorMessage());
        }
    }

    @Override
    public void print() {
        System.out.println("Ручной ввод данных о недвижимости:");
    }
}
