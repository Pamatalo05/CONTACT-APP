package com.espol.group04.grupo_04.Clases;

public class PhoneNumber {
    public String number;
    public String type; 

    public PhoneNumber(String number, String type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + ": " + number;
    }
    
}
