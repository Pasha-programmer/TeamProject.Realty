package com.example.Domain.Models.Comparators;

import com.example.Domain.Models.RealtyDto;

import java.util.Comparator;
import java.util.Objects;

public class RealtyComparator implements Comparator<RealtyDto> {
    @Override
    public int compare(RealtyDto realtyDto1, RealtyDto realtyDto2) {
        if (realtyDto1 == null && realtyDto2 == null){
            return 0;
        }

        if (realtyDto1 == null){
            return -1;
        }

        if (realtyDto2 == null){
            return 1;
        }

        if (!Objects.equals(realtyDto1.getAddress(), realtyDto2.getAddress())){
            return realtyDto1.getAddress().compareTo(realtyDto2.getAddress());
        }

        if (realtyDto1.getTotalArea() != realtyDto2.getTotalArea()){
            return Double.compare(realtyDto1.getTotalArea(), realtyDto2.getTotalArea());
        }

        if (!Objects.equals(realtyDto1.getCost(), realtyDto2.getCost())){
            return realtyDto1.getCost().compareTo(realtyDto2.getCost());
        }

        return 0;
    }
}
