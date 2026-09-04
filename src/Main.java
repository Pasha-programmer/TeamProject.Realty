import ConsoleUI.Menu.Contracts.Common.ConsoleStageMenu;
import ConsoleUI.Menu.MainMenu;
import Infrastructure.Services.Realty.RealtyGetterService;

void main() {

    ConsoleStageMenu mainMenu = new MainMenu(
        new RealtyGetterService()
    );
    mainMenu.run();
}
