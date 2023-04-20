package by.gurinovich.proj3SprtingProj.utils;

import by.gurinovich.proj3SprtingProj.dto.MeasurementDTO;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
