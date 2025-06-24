/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group05.grupo_05.Classes;

import java.util.Date;

/**
 *
 * @author misae
 */
public class ImportantDate {
    public Date date;
    public String name;
    public String desciption;
    @Override
    public String toString() {
        return name +","+ date.toString()+","+desciption;
    }
    @SuppressWarnings("deprecation")
    public ImportantDate(String[] args){
        if(args.length<4){throw new IllegalArgumentException("Illegal Argument");}
        name = args[0];
        date = new Date(args[1]);
        desciption = args[2];
    }
    
}