package com.espol.group05.grupo_05.Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.espol.group05.grupo_05.Classes.Email;
import com.espol.group05.grupo_05.Classes.ImportantDate;
import com.espol.group05.grupo_05.Classes.Location;
import com.espol.group05.grupo_05.Classes.PhoneNumber;
import com.espol.group05.grupo_05.Classes.Photo;
import com.espol.group05.grupo_05.Contacts.Company;
import com.espol.group05.grupo_05.Contacts.Contact;
import com.espol.group05.grupo_05.Contacts.NaturalPerson;

public class InformationManage {
    public static void saveContactData(String contactsDirectory,
                                    String userName,
                                    Contact contact) throws IOException {
        
        Path userDir = Paths.get(contactsDirectory, userName);
        if (!Files.exists(userDir)) {
            Files.createDirectories(userDir);
        }
        String pathPhones = userDir.toString()+"/"+userName+"_Phones.txt";
        String pathPhotos = userDir.toString()+"/"+userName+"_Photos.txt";
        String pathEmails = userDir.toString()+"/"+userName+"_Emails.txt";
        String pathLocations = userDir.toString()+"/"+userName+"_Locations.txt";
        String pathDates = userDir.toString()+"/"+userName+"_ImportantDates.txt";
        String pathContacts = userDir.toString()+"/"+userName+"_Contacts.txt";
        Literacy.writeFile(contact.getPhoneNumbers(), pathPhones, Object :: toString , "\n");
        Literacy.writeFile(contact.getPhotos(), pathPhotos, Object :: toString , "\n");
        Literacy.writeFile(contact.getLocations(), pathLocations, Object :: toString , ",");
        Literacy.writeFile(contact.getEmails(), pathEmails, Object :: toString , "\n");
        Literacy.writeFile(contact.getImportantDates(), pathDates, Object :: toString , "\n");
        Literacy.writeFile(contact.getRelatedContacts(), pathContacts, Object :: toString , "\n");
    }

    public static Contact getContactData(String path, Contact contact) throws IOException{
        Contact contactCurrent = contact;
        String completePath = path+"/"+contactCurrent.getUser()+"_";
        contact.setPhoneNumbers(Literacy.readFile(completePath+"Phones.txt",parts -> new PhoneNumber(parts), ","));
        contact.setPhotos(Literacy.readFile(completePath+"Photos.txt",parts -> new Photo(parts), ","));
        contact.setLocations(Literacy.readFile(completePath+"Locations.txt",parts -> new Location(parts), ","));
        contact.setEmails(Literacy.readFile(completePath+"Emails.txt",parts -> new Email(parts), ","));
        contact.setImportantDates(Literacy.readFile(completePath+"ImportantDates.txt",parts -> new ImportantDate(parts), ","));
        contact.setRelatedContacts(Literacy.readFile(completePath+"Contacts.txt", parts -> {
        if (parts[0].equals("NP")) {
            return new NaturalPerson(Arrays.copyOfRange(parts, 1, parts.length));
        } else if (parts[0].equals("C")) {
            return new Company(Arrays.copyOfRange(parts, 1, parts.length));
        } else {
            throw new IllegalArgumentException("Unknown contact type");
        }
        }, ","));
        return contact;
    }
}
