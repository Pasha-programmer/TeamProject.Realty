package ConsoleUI.Menu.Components;

import Domain.Contracts.Realty.RealtyGetter;
import Domain.Models.RealtyDto;

/**
 * Компонент отображения списка недвижимости.
 */
public class RealtyListComponent extends ConsoleComponent {

    public RealtyListComponent(RealtyGetter realtyGetter){
        this.realtyGetter = realtyGetter;
    }

    private final RealtyGetter realtyGetter;

    @Override
    public void print(){
        var data = realtyGetter.getRealty(null);

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
        return "Адрес: " + realty.address + "\tПлощадь: " + realty.totalArea + "м2 \tСтоимость: " + realty.cost + "руб.";
    }
}
