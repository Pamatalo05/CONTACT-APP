package com.espol.group05.grupo_05.Classes;

public class SocialMedia {
    String userName;
    String linkProfile;
    @Override
    public String toString() {
        return userName+","+linkProfile;
    }
    public SocialMedia(String[] args){
        userName = args[0];
        linkProfile = args[1];
    }
    public String getLinkProfile() {
        return linkProfile;
    }
    public String getUserName() {
        return userName;
    }
}
