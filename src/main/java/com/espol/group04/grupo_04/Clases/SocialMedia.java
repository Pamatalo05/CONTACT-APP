package com.espol.group04.grupo_04.Clases;

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
}
