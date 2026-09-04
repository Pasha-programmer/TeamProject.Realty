package Domain.Validators;

import Domain.Models.BusinessError;
import Domain.Contracts.Validators.Validator;
import Domain.Models.RealtyDto;

import java.math.BigDecimal;

/**
 * Валидатор модели недвижимости
 */
public class RealtyDtoValidator extends Validator<RealtyDto> {

    @Override
    public BusinessError validate(RealtyDto model) {
        if (model.getCost().compareTo(BigDecimal.ZERO) <= 0){
            return new BusinessError("Стоимость не может быть отрицательной или нулем.");
        }

        if (model.getTotalArea() <= 0){
            return new BusinessError("Площадь не может быть отрицательной или нулем.");
        }

        if (model.getAddress().isBlank()){
            return new BusinessError("Адрес должен быть заполненным.");
        }

        return null;
    }
}
