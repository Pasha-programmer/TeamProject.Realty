package com.example.Infrastructure.Services.Realty;

import java.util.Random;

// генератор рандомных дом-объектов

public class GenerationRandomHouse {

        private String street;
        private int square;
        private long price;

        public GenerationRandomHouse(String street, int square, long price) {
            this.street = street;
            this.square = square;
            this.price = price;
        }

        public String getStreet() {
            return street;
        }
        public int getSquare() {
            return square;
        }
        public long getPrice() {
            return price;
        }

        private static final Random random = new Random();
        private static final String[] streets = {
                "Пушкина", "Ленина", "Широкая ", "Преображенская", "Пионерская",
                "Длинная", "Университетская", "Зеленоградская", "Вавилова"
        };

        public static GenerationRandomHouse generate() {

            String street = streets[random.nextInt(streets.length)]; // рандом ул из списка
            int square = random.nextInt(300);
            long price = random.nextLong(100000) + 1000000;

            return new GenerationRandomHouse(street, square, price);
        }
        @Override
        public String toString() {

            StringBuilder result = new StringBuilder();

            result.append("Ул").append(street).append(" Площадь:  ").append(square).append("   Цена:  ").append(price);
            return result.toString();

        }}




