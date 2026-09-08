package ConsoleUI.Menu.Components;

import Domain.Contracts.Realty.RealtyGetter;
import Domain.Models.RealtyFilterParametersDto;

/**
 * Компонент отображения количества найденных недвижимостей.
 */
public class RealtyListCountComponent extends ConsoleComponent {

    public RealtyListCountComponent(RealtyGetter realtyGetter, RealtyFilterParametersDto filterParameters){
        this.realtyGetter = realtyGetter;
        this.filterParameters = filterParameters;
    }

    private final RealtyGetter realtyGetter;
    private RealtyFilterParametersDto filterParameters;

    @Override
    public void print(){
        var realtyCount = realtyGetter.getRealtyCount(filterParameters);

        if (realtyCount == null){
            System.err.println("Не удалось получить данные");
            return;
        }

        System.out.println(FormatRealty(realtyCount));
    }

    private String FormatRealty(int count){
        return "Количество записей: " + count;
    }

    public void setFilterParameters(RealtyFilterParametersDto filterParameters){
        this.filterParameters = filterParameters;
    }
}
