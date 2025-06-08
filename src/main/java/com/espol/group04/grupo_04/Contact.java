/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04;

import com.espol.group04.grupo_04.Utilities.DoubleCircularList;
public class Contact {

    private String name;
    private String address;

    private DoubleCircularList<PhoneNumber> phoneNumbers;
    private DoubleCircularList<Email> emails;
    private DoubleCircularList<SocialMedia> socialMediaAccounts;
    private DoubleCircularList<ImportantDate> importantDates;
    private DoubleCircularList<String> photos; 
    private DoubleCircularList<Contact> relatedContacts;

    public Contact(String name, String address) {
        this.name = name;
        this.address = address;
        this.phoneNumbers = new DoubleCircularList<PhoneNumber>();
        this.emails = new DoubleCircularList<Email>();
        this.socialMediaAccounts = new DoubleCircularList<SocialMedia>();
        this.importantDates = new DoubleCircularList<ImportantDate>();
        this.photos = new DoubleCircularList<String>();
        this.relatedContacts = new DoubleCircularList<Contact>();
    }
    
    
    public void addPhoneNumber(PhoneNumber phone) {
        phoneNumbers.addLast(phone);
    }

    public void addEmail(Email email) {
        emails.addLast(email);
    }

    public void addSocialMedia(SocialMedia sm) {
        socialMediaAccounts.addLast(sm);
    }

    public void addImportantDate(ImportantDate date) {
        importantDates.addLast(date);
    }

    public void addPhoto(String photo) {
        photos.addLast(photo);
    }

    public void addRelatedContact(Contact contact) {
        relatedContacts.addLast(contact);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public DoubleCircularList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public DoubleCircularList<Email> getEmails() {
        return emails;
    }

    public DoubleCircularList<SocialMedia> getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public DoubleCircularList<ImportantDate> getImportantDates() {
        return importantDates;
    }

    public DoubleCircularList<String> getPhotos() {
        return photos;
    }

    public DoubleCircularList<Contact> getRelatedContacts() {
        return relatedContacts;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumbers(DoubleCircularList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setEmails(DoubleCircularList<Email> emails) {
        this.emails = emails;
    }

    public void setSocialMediaAccounts(DoubleCircularList<SocialMedia> socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public void setImportantDates(DoubleCircularList<ImportantDate> importantDates) {
        this.importantDates = importantDates;
    }

    public void setPhotos(DoubleCircularList<String> photos) {
        this.photos = photos;
    }

    public void setRelatedContacts(DoubleCircularList<Contact> relatedContacts) {
        this.relatedContacts = relatedContacts;
    }

    @Override
    public String toString() {
        return name + " (" + address + ")";
    }
}
