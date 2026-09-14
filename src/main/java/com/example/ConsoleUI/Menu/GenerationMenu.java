package com.example.ConsoleUI.Menu;
import  com.example.Infrastructure.Services.Realty.GenerationRandomHouse;
import java.util.Scanner;

// созддаёт меню генерации

public class GenerationMenu {

    public enum MenuAction {

        ADD1("Генерация", () -> {

            System.out.println("Число сгенерированных домов:");
            Scanner scanner = new Scanner(System.in);

            String street = "";
            int square = 0;
            float price = 0;

            GenerationRandomHouse runGenerationHouse = new GenerationRandomHouse(street, square, (long) price);
            boolean valid = false;
            int choice = 0;

            while (!valid) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice > 0) {
                        valid = true;

                        for (int i = 0; i < choice; i++) {
                            GenerationRandomHouse obj = GenerationRandomHouse.generate();
                            System.out.println(obj + "\n");
                        }
                    } else {
                        System.out.println("Ошибка: введите число > 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число");
                }
            }

        }),
        ADD2("Импорт из файла", () -> System.out.println("Добавлено!")),
        ADD3("Ввод в ручную", () -> System.out.println("Добавлено!")),

        //REMOVE("Удалить запись", () -> System.out.println("Удалено!")),
        //SHOW("Показать все", () -> System.out.println("Список: ...")),
        EXIT("Назад", () -> System.out.println("До свидания!"));

        private final String title;
        private final Runnable action;

        MenuAction(String title, Runnable action) {
            this.title = title;
            this.action = action;
        }

        public String getTitle() {
            return title;
        }

        public void execute() {
            action.run();


        }

        public static void printMenu() {
            System.out.println("\n=== Создать данные о недвижимости ===");
            MenuAction[] items = values();
            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %s%n", i + 1, items[i].getTitle());
            }
            System.out.print("Ваш выбор: ");
        }

        public static MenuAction fromNumber(int n) {
            MenuAction[] items = values();
            return (n >= 1 && n <= items.length) ? items[n - 1] : null;
        }


        public static void getChoice (){

        while (true) {
            Scanner scanner = new Scanner(System.in);
            GenerationMenu.MenuAction.printMenu();
            int choice = scanner.nextInt();

            GenerationMenu.MenuAction action = GenerationMenu.MenuAction.fromNumber(choice);
            if (action == null) {
                System.out.println("Неверный пункт, попробуйте снова.");
                continue;
            }

            action.execute();


            if (action == GenerationMenu.MenuAction.EXIT) break;

        }}

    }
}/*
class Main {
    public static void main(String[] args) {

        GenerationMenu.MenuAction.getChoice();}

}
*/