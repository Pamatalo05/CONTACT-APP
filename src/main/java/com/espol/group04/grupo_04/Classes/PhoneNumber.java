package com.espol.group05.grupo_05.Classes;


public class PhoneNumber {
    public String phoneNumber;
    public Utilization utilization;
    @Override
    public String toString() {
        return utilization+","+phoneNumber;
    }

    public PhoneNumber(String[] parts){
        if(parts.length<3){throw new IllegalArgumentException("Illegal argument");}
        this.utilization = new Utilization(parts[0]);
        this.phoneNumber = parts[1];
    }
}
