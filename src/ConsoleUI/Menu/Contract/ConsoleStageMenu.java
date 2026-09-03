package ConsoleUI.Menu.Contract;

/**
 * Базовое представление меню в консоли.
 */
public abstract class ConsoleStageMenu {

    /**
     * Запустить отображение меню и ее обработку.
     */
    public abstract void run();

    /**
     * Отобразить ошибку о некорректном выборе опции из меню.
     */
    protected void printWrongChoiceError(){
        System.out.println("Некорректный выбор");
    }
}
