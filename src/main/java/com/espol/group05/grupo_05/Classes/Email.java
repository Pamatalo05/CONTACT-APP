/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group05.grupo_05.Classes;

/**
 *
 * @author misae
 */
public class Email {
    public String address;
    public Utilization label;

    public Email(String address) {
        this.address = address;
        this.label = null;
    }
    public Email(String address, Utilization label) {
        this.address = address;
        this.label = label;
    }

    @Override
    public String toString() {
        return label + "," + address;
    }
    
    public String getAddress(){
        return this.address;
    }

    public Email(String[] args){
        label = new Utilization(args[0]);
        address = args[1];
    }
}
