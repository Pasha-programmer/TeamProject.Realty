package Domain.Contracts.Monads;

import Domain.Models.BusinessError;

/**
 * Обертка над результатом.
 *
 * @param <V> Тип результата.
 */
public record Result<V>(V value, BusinessError error) {
    public Result(V value){
        this(value, null);
    }
}
