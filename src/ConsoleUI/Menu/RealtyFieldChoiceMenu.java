package ConsoleUI.Menu;

import ConsoleUI.Menu.Components.*;
import ConsoleUI.Menu.Contracts.Models.Common.ConsoleStageMenu;
import ConsoleUI.Menu.Contracts.Models.Common.InputComponent;
import ConsoleUI.Menu.Contracts.Models.Enums.SearchByFieldMenuOptions;
import ConsoleUI.Menu.Contracts.Strategies.CloseMenuStrategy;
import ConsoleUI.Menu.Contracts.Strategies.NotImplementedStrategy;
import ConsoleUI.Menu.Contracts.Strategies.ShowDataStrategy;
import Domain.Contracts.Realty.RealtyGetter;
import Domain.Models.RealtyFilterParametersDto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Этап меню выбора поля для фильтрации.
 */
public class RealtyFieldChoiceMenu extends ConsoleStageMenu {

    private final RealtyListCountComponent realtyListCountComponent;
    private final InputComponent<String> inputStringComponent;
    private final InputComponent<BigDecimal> inputMoneyComponent;
    private final InputComponent<Double> inputDoubleComponent;

    public RealtyFieldChoiceMenu(RealtyGetter realtyGetter){
        this.realtyListCountComponent = new RealtyListCountComponent(realtyGetter, null);
        this.inputStringComponent = new InputStringComponent();
        this.inputMoneyComponent = new InputMoneyComponent();
        this.inputDoubleComponent = new InputDoubleComponent();
    }

    private final static SortedMap<SearchByFieldMenuOptions, String> menuOptionsMap = new TreeMap<>(
        Map.ofEntries(
            Map.entry(SearchByFieldMenuOptions.Address, "По адресу"),
            Map.entry(SearchByFieldMenuOptions.Cost, "По стоимости"),
            Map.entry(SearchByFieldMenuOptions.TotalArea, "По общей площади"),
            Map.entry(SearchByFieldMenuOptions.Cancel, "Отмена")
        )
    );

    @Override
    public void run() {
        while(isRun()){
            print();

            var choice = readOption(SearchByFieldMenuOptions.class);

            var realtyFilterParametersBuilder = RealtyFilterParametersDto.RealtyFilterParametersBuilder.create();

            var action = switch (choice){
                case SearchByFieldMenuOptions.Address -> {
                    var address = inputStringComponent.read();
                    var filters = realtyFilterParametersBuilder
                        .setAddress(address)
                        .build();
                    realtyListCountComponent.setFilterParameters(filters);
                    yield new ShowDataStrategy(realtyListCountComponent);
                }
                case SearchByFieldMenuOptions.Cost -> {
                    var cost = inputMoneyComponent.read();
                    var filters = realtyFilterParametersBuilder
                            .setFromCost(cost)
                            .setToCost(cost)
                            .build();
                    realtyListCountComponent.setFilterParameters(filters);
                    yield new ShowDataStrategy(realtyListCountComponent);
                }
                case SearchByFieldMenuOptions.TotalArea -> {
                    var totalArea = inputDoubleComponent.read();
                    var filters = realtyFilterParametersBuilder
                            .setFromTotalArea(totalArea)
                            .setToTotalArea(totalArea)
                            .build();
                    realtyListCountComponent.setFilterParameters(filters);
                    yield new ShowDataStrategy(realtyListCountComponent);
                }
                case SearchByFieldMenuOptions.Cancel ->
                    new CloseMenuStrategy();
                default ->
                    new NotImplementedStrategy("" + choice.getValue());
            };

            // Выполняем стратегию и получаем результат
            var result = action.execute();

            // Обрабатываем результат
            processResult(result);
        }
    }

    @Override
    public void print() {
        menuOptionsMap.forEach((key, value) -> {
            System.out.println("\t" + key.getValue() + ". " + value);
        });

        System.out.print("Выберите поле, по которому требуется поиск: ");
    }
}
