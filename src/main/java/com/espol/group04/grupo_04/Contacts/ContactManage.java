package com.espol.group05.grupo_05.Contacts;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import com.espol.group05.grupo_05.Utilities.DoubleCircularList;
import com.espol.group05.grupo_05.Utilities.Literacy;

public class ContactManage {
    public static Contact validateCredentials(String user, String password) {
        Function<String[], Contact> contactMapper = parts -> {
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid credentials format");
            }
            if(isCompany(parts[0])){
                Company contact = new Company(parts);
                return contact;
            }else{
                NaturalPerson contact = new NaturalPerson(parts);
                return contact;
            }
        };

        DoubleCircularList<Contact> credentialsList;
        String projectRoot = System.getProperty("user.dir");
        String dataPath = projectRoot + File.separator + "data" + File.separator;
        new File(dataPath).mkdirs();
        String credentialsFilePath = dataPath + "credentials.txt";
        try {
            credentialsList = Literacy.readFile(credentialsFilePath, contactMapper, ",");
        } catch (IOException e) {
            System.err.println("Error reading credentials: " + e.getMessage());
            return null;
        }
        for (Contact contact : credentialsList) {
            if (contact.validation(user.trim(), password.trim())) {
                return contact;
            }
        }
        return null;
    }

    public static boolean isCompany(String s){
        if(s.toUpperCase().equals("C")){return true;}
        return false;
    }
}
