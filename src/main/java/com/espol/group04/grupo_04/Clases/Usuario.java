package com.espol.group04.grupo_04.Clases;

public abstract class Usuario {
    String numeroTelefono;
    String direccion;
    String correo;
    String nombre;
    String tipo;
    Foto fotos;

    public Usuario(String numeroTelefono, String nombre, String tipo) {
        this.numeroTelefono = numeroTelefono;
        this.nombre = nombre;
        this.tipo = tipo;
    }
}
