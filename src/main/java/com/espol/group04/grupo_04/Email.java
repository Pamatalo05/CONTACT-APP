/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04;

/**
 *
 * @author misae
 */
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
