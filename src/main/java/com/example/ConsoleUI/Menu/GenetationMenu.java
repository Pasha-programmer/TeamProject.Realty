package com.example.ConsoleUI.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenetationMenu {

    import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//генерация меню, чтоение ввода

    public class MenuGenetation{

        private List<Action> menu_additions;
        private Scanner scanner;

        public MenuGenetation () {

            this.menu_additions = new ArrayList<>();
            this.scanner = new Scanner(System.in);

            // меню пункты

            menu_additions.add(new RandomGeneration());
            menu_additions.add(new ImportFile());
            menu_additions.add(new ManualInput());
            menu_additions.add(new Back(this));
        }

        public void display() {
            System.out.println("\n==========++++++==========");

            for (int i = 0; i < menu_additions.size(); i++) {
                System.out.println((i + 1) + ". " + menu_additions.get(i).getDescription());
            }
            System.out.println("============================");
        }

        public int getChoice() { //**********

            int choice = 0;
            boolean valid = false;

            while (!valid){

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= menu_additions.size()) {
                        valid = true;
                    } else {
                        System.out.println("Ошибка: введите число от 1 до " + menu_additions.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число");
                }

            }
            return choice;
        }
        public void processChoice(int choice) {

            menu_additions.get(choice - 1).execute();
        }

        public void run() {
            while (true) {
                display();
                int choice = getChoice();
                processChoice(choice);
            }
        }

        static class MenuExitException extends RuntimeException {
            // Пустое исключение для управления потоком
        }

    }


}
