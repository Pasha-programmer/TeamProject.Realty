package com.example.ConsoleUI.Menu.Components;

import com.example.ConsoleUI.Menu.Contracts.Models.Common.InputComponent;

import java.util.Scanner;

/**
 * Компонент ввода текстовой информации от пользователя.
 */
public class InputStringComponent extends InputComponent<String> {

    public InputStringComponent(Scanner scanner){
        super(scanner);
    }

    @Override
    public void print() {
        System.out.print("Введите строку: ");
    }

    @Override
    public String read() {
        print();

        return readString();
    }

    protected String readString(){
        return scanner.nextLine().trim();
    }
}
