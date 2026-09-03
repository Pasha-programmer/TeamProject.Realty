package Domain.Validators;

import Domain.Contracts.Monads.BusinessError;
import Domain.Contracts.Validators.Validator;
import Domain.Models.RealtyDto;

import java.math.BigDecimal;

/**
 * Валидатор модели недвижимости
 */
public class RealtyDtoValidator extends Validator<RealtyDto> {

    @Override
    public BusinessError Validate(RealtyDto model) {
        if (model.cost.compareTo(BigDecimal.ZERO) <= 0){
            return new BusinessError("Стоимость не может быть отрицательной или нулем.");
        }

        if (model.totalArea <= 0){
            return new BusinessError("Площадь не может быть отрицательной или нулем.");
        }

        if (model.address.isBlank()){
            return new BusinessError("Адрес должен быть заполненным.");
        }

        return null;
    }
}
