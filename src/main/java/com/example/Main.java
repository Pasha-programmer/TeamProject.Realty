package com.example;
import com.example.ConsoleUI.Menu.Contracts.Common.ConsoleStageMenu;
import com.example.ConsoleUI.Menu.MainMenu;
import com.example.Domain.Models.RealtyDto;
import com.example.Infrastructure.Services.Realty.RealtyGetterService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.DataAccess.Realty.RealtyDao;

public class Main {
    public static void main(String[] args) {

        ConsoleStageMenu mainMenu = new MainMenu(
                new RealtyGetterService()
        );
        mainMenu.run();

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            RealtyDao house = mapper.readValue("{\n" +
//                    "    \"address\": \"3948 Adams Rd, Fairview, 29481\",\n" +
//                    "    \"cost\": 629104,\n" +
//                    "    \"totalArea\": 218.4\n" +
//                    "  },\n", RealtyDao.class);
//            System.out.println(house.address + " - " + house.totalArea);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
