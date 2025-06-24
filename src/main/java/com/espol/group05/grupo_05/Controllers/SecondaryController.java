package com.espol.group05.grupo_05.Controllers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.espol.group05.grupo_05.Classes.Email;
import com.espol.group05.grupo_05.Classes.Location;
import com.espol.group05.grupo_05.Classes.PhoneNumber;
import com.espol.group05.grupo_05.Classes.SocialMedia;
import com.espol.group05.grupo_05.Contacts.Contact;
import com.espol.group05.grupo_05.Utilities.DataHolder;
import com.espol.group05.grupo_05.Utilities.InformationManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondaryController implements Initializable{

    @FXML
    private Button Manage_Contacts_Button;
    Contact contactUser;
    @FXML
    private AnchorPane scrollUserContent;
    @FXML
    private AnchorPane scrollUserInfoContent;
    @FXML
    private AnchorPane scrollPhonesContent;
    @FXML
    private AnchorPane scrollEmailsContent;
    @FXML
    private AnchorPane scrollSocialContent;
    @FXML
    private AnchorPane scrollAddressesContent;

    @FXML
    void handleClick(ActionEvent event) throws IOException {
        DataHolder.getInstance().setMyData(contactUser);
        Parent thirdParent = FXMLLoader.load(getClass().getResource("/com/espol/group04/grupo_04/tertiarity.fxml"));
        Scene thirdScene = new Scene(thirdParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(thirdScene);
        window.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactUser = (Contact) DataHolder.getInstance().getMyData();
        String projectRoot = System.getProperty("user.dir");
        String dataPath = projectRoot + File.separator + "data" + File.separator;
    
        try {
            contactUser = InformationManage.getContactData(dataPath, contactUser);
            loadContactData();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContactData() {
        loadUserInfo();
        loadPhones();
        loadEmails();
        loadSocialMedia();
        loadAddresses();
    }

    private void loadUserInfo() {
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
    
        Label nameLabel = new Label("Usuario: " + contactUser.getUser());
        vbox.getChildren().addAll(nameLabel);
        scrollUserContent.getChildren().add(vbox);
    }

    private void loadPhones() {
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(5));
    
        if (contactUser.getPhoneNumbers() != null) {
            for (PhoneNumber phone : contactUser.getPhoneNumbers()) {
                Label phoneLabel = new Label(phone.toString()); // Asume que PhoneNumber tiene toString()
                vbox.getChildren().add(phoneLabel);
            }
        }
    
        scrollPhonesContent.getChildren().add(vbox);
    }

    private void loadEmails() {
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(5));
    
        if (contactUser.getEmails() != null) {
            for (Email email : contactUser.getEmails()) {
                Label emailLabel = new Label(email.toString()); // Asume que Email tiene toString()
                vbox.getChildren().add(emailLabel);
            }
        }
    
        scrollEmailsContent.getChildren().add(vbox);
    }

    private void loadSocialMedia() {
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(5));
    
        if (contactUser.getSocialMediaAccounts() != null) {
            for (SocialMedia social : contactUser.getSocialMediaAccounts()) {
                Label socialLabel = new Label(social.toString()); // Asume que SocialMedia tiene toString()
                vbox.getChildren().add(socialLabel);
            }
        }
    
        scrollSocialContent.getChildren().add(vbox);
    }

    private void loadAddresses() {
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(5));
    
        if (contactUser.getLocations() != null) {
            for (Location location : contactUser.getLocations()) {
                Label locationLabel = new Label(location.toString()); // Asume que Location tiene toString()
                vbox.getChildren().add(locationLabel);
            }
        }
    
        scrollAddressesContent.getChildren().add(vbox);
    }

}
