/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04;

/**
 *
 * @author misae
 */
public class ImportantDate {
    public String label; 
    public String date;

    public ImportantDate(String label, String date) {
        this.label = label;
        this.date = date;
    }

    @Override
    public String toString() {
        return label + ": " + date;
    }
    
    public String getLabel(){
        return this.label;
    }
    
    public String getDate(){
        return this.date;
    }
}