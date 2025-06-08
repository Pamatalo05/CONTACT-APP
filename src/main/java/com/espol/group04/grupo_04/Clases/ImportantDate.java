package com.espol.group04.grupo_04.Clases;

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
    
}
