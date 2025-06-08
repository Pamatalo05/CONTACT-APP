package com.espol.group04.grupo_04.Clases;

public class Email {
    public String address;
    public String label; 

    public Email(String address, String label) {
        this.address = address;
        this.label = label;
    }

    @Override
    public String toString() {
        return label + ": " + address;
    }
}
