/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04;

/**
 *
 * @author misae
 */
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

