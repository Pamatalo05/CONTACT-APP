package com.espol.group05.grupo_05.Classes;

public class Enviorment {
    String country;
    String name;
    @Override
    public String toString() {
        return name+","+country;
    }

    public Enviorment(String name){
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
}
