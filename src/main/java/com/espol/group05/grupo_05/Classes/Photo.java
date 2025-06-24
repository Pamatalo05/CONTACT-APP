package com.espol.group05.grupo_05.Classes;

public class Photo {
    public String path;
    public String name;
    @Override
    public String toString() {
        return name+","+path;
    }
    public Photo(String[] args){
        name = args[0];
        path = args[1];
    }

}
