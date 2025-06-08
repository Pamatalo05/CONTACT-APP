/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04;


public class SocialMedia {
    public String platform;
    public String username;

    public SocialMedia(String platform, String username) {
        this.platform = platform;
        this.username = username;
    }

    @Override
    public String toString() {
        return platform + ": " + username;
    }
    
    public String getPlatform(){
        return this.platform;
    }
    public String getUsername(){
        return this.username;
    }
}
