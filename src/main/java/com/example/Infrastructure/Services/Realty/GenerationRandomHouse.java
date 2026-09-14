package com.example.Infrastructure.Services.Realty;

import com.example.Domain.Models.RealtyDto;

import java.math.BigDecimal;
import java.util.Random;

// генератор рандомных дом-объектов

public class GenerationRandomHouse {

        private String adres;
        private int area;
        private long price;

        public GenerationRandomHouse(String street, int square, long price) {
            this.adres = street;
            this.area = square;
            this.price = price;
        }

        public String getStreet() {
            return adres;
        }
        public int getSquare() {
            return area;
        }
        public long getPrice() {
            return price;
        }

        private static final Random random = new Random();
        private static final String[] streets = {
                "Пушкина", "Ленина", "Широкая ", "Преображенская", "Пионерская",
                "Длинная", "Университетская", "Зеленоградская", "Вавилова"
        };

        public static RealtyDto generate() {

            String street = streets[random.nextInt(streets.length)]; // рандом ул из списка
            int square = random.nextInt(300);
            long price = random.nextLong(100000) + 1000000;

            BigDecimal cost =  new BigDecimal(price);

            return  RealtyDto.RealtyBuilder.create().setAddress(street).setCost(cost).setTotalArea(square).build();

        }
        }




