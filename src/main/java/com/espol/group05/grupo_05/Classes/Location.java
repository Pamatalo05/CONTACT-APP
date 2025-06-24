package com.espol.group05.grupo_05.Classes;

public class Location {
    Enviorment type;
    private String googleMapsLink;
    public Location(Enviorment type, String googleMapsLink){
        this.type = type;
        this.googleMapsLink = googleMapsLink;
    }
    public String getGoogleMapsLink() {
        return googleMapsLink;
    }
    @Override
    public String toString() {
        return type.toString()+","+googleMapsLink;
    }
    public Location(String[] parts){
        if(parts.length<3){throw new IllegalArgumentException("Illegal argument");}
        this.type = new Enviorment(parts[0]);
        this.googleMapsLink = parts[1];
    }
    public Enviorment getType() {
        return type;
    }

}
