package Domain.Contracts.Monads;

/**
 * Модель ошибки бизнеса.
 */
public final class BusinessError {

    public BusinessError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Детали ошибки.
     */
    private final String errorMessage;
}
