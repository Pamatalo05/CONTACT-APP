/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group05.grupo_05.Contacts;

import com.espol.group05.grupo_05.Classes.Email;
import com.espol.group05.grupo_05.Classes.Home;
import com.espol.group05.grupo_05.Classes.ImportantDate;
import com.espol.group05.grupo_05.Classes.InterfaceVerifiable;
import com.espol.group05.grupo_05.Classes.Location;
import com.espol.group05.grupo_05.Classes.PhoneNumber;
import com.espol.group05.grupo_05.Classes.Photo;
import com.espol.group05.grupo_05.Classes.SocialMedia;

/**
 *
 * @author misae
 */
import com.espol.group05.grupo_05.Utilities.DoubleCircularList;
public abstract class Contact implements Comparable<Contact>, InterfaceVerifiable{
    private DoubleCircularList<PhoneNumber> phoneNumbers;
    private DoubleCircularList<Email> emails;
    private DoubleCircularList<SocialMedia> socialMediaAccounts;
    private DoubleCircularList<ImportantDate> importantDates;
    private DoubleCircularList<Contact> relatedContacts;
    private DoubleCircularList<Location> locations;
    private DoubleCircularList<Photo> photos;
    private String user;
    private String password;

    public boolean validation(String user, String password){
        return (this.user==user && this.password==password);
    }


    public Contact(String[] credentials){
        if(credentials.length!=3){
            throw new IllegalArgumentException("Credentials are just three Strings");
        }
        this.user=credentials[1];
        this.password=credentials[2];
    }

    public String toCredentialString(String delimiter) {
        return user+delimiter+password;
    }

    public DoubleCircularList<Email> getEmails() {
        return emails;
    }
    public DoubleCircularList<ImportantDate> getImportantDates() {
        return importantDates;
    }
    public DoubleCircularList<Location> getLocations() {
        return locations;
    }
    public DoubleCircularList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
    public DoubleCircularList<Photo> getPhotos() {
        return photos;
    }
    public DoubleCircularList<Contact> getRelatedContacts() {
        return relatedContacts;
    }
    public DoubleCircularList<SocialMedia> getSocialMediaAccounts() {
        return socialMediaAccounts;
    }
    public String getUser() {
        return user;
    }
    public void setEmails(DoubleCircularList<Email> emails) {
        this.emails = emails;
    }
    public void setImportantDates(DoubleCircularList<ImportantDate> importantDates) {
        this.importantDates = importantDates;
    }
    public void setLocations(DoubleCircularList<Location> locations) {
        this.locations = locations;
    }
    public void setPhoneNumbers(DoubleCircularList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public void setPhotos(DoubleCircularList<Photo> photos) {
        this.photos = photos;
    }
    public void setRelatedContacts(DoubleCircularList<Contact> relatedContacts) {
        this.relatedContacts = relatedContacts;
    }
    public void setSocialMediaAccounts(DoubleCircularList<SocialMedia> socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public String getCountry(){
        for(Location l: locations){
            if(l.getType() instanceof Home){
                return l.getType().getCountry();
            }
        }
        return null;
    }
}
