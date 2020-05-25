package com.example.demo;

public class CityDo {

    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    private CityDo(){}

    public CityDo (String name, Double value) {
        this.name = name;
        this.value = value;

    }
}
