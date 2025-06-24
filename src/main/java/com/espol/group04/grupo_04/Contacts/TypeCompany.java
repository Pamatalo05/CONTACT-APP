package com.espol.group05.grupo_05.Contacts;

public class TypeCompany {
    String designation;
    @Override
    public String toString() {
        return designation;
    }
    public TypeCompany(String designation){
        this.designation=designation;
    }
}
