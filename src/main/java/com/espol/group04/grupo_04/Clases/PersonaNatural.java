package com.espol.group04.grupo_04.Clases;

public class PersonaNatural extends Usuario{
    String apellido;
    String fechaNacimiento;
    public PersonaNatural(String numeroTelefono, String nombre, String apellido, String fechaNacimiento) {
        super(numeroTelefono, nombre, "persona");
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }
}
