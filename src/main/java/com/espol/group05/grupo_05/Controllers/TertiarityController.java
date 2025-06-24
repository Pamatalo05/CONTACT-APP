package com.espol.group05.grupo_05.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

import com.espol.group05.grupo_05.Contacts.Contact;
import com.espol.group05.grupo_05.Contacts.Company;
import com.espol.group05.grupo_05.Contacts.NaturalPerson;
import com.espol.group05.grupo_05.Utilities.DataHolder;
import com.espol.group05.grupo_05.Utilities.InformationManage;
import com.espol.group05.grupo_05.Classes.PhoneNumber;
import com.espol.group05.grupo_05.Classes.Email;
import com.espol.group05.grupo_05.Classes.Photo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TertiarityController {

    private Contact contact;
    private String contactsDirectory = "path/to/contacts/directory";

    @FXML
    private Button addButton;

    @FXML
    private Button filterButton;

    @FXML
    private Button sortButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void addHandleClick(ActionEvent event) throws IOException {
        Parent fourthParent;
        Scene fourtScene;
        Stage window;
        
        Contact newContactType = template();
        if(newContactType instanceof Company) {
            fourthParent = FXMLLoader.load(getClass().getResource("/com/espol/group04/grupo_04/quinary.fxml"));
        } else {
            fourthParent = FXMLLoader.load(getClass().getResource("/com/espol/group04/grupo_04/quaternary.fxml"));
        }
        DataHolder.getInstance().setMyData(contact);
        fourtScene = new Scene(fourthParent);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fourtScene);
        window.show();
    }

    private Contact template() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Natural Person", "Natural Person", "Company");
        dialog.setTitle("New Contact Type");
        dialog.setHeaderText("Select Contact Type");
        dialog.setContentText("Choose the type of contact you want to create:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals("Company")) {
                return new Company(new String[]{"", "", ""});
            } else {
                return new NaturalPerson(new String[]{"", "", ""});
            }
        }
        return null;
    }

    @FXML
    private void filterHandleClick(ActionEvent event) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("All", "All", "Natural Persons", "Companies", "By Country");
        dialog.setTitle("Filter Contacts");
        dialog.setHeaderText("Filter Options");
        dialog.setContentText("Choose how to filter contacts:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                Contact currentContact = InformationManage.getContactData(contactsDirectory, contact);
                VBox contactsContainer = new VBox(10);
                contactsContainer.setPadding(new javafx.geometry.Insets(10));
                
                for (Contact relatedContact : currentContact.getRelatedContacts()) {
                    Contact loadedContact = InformationManage.getContactData(contactsDirectory, relatedContact);
                    
                    boolean shouldShow = true;
                    switch (result.get()) {
                        case "Natural Persons":
                            shouldShow = loadedContact instanceof NaturalPerson;
                            break;
                        case "Companies":
                            shouldShow = loadedContact instanceof Company;
                            break;
                        case "By Country":
                            String country = showCountryInputDialog();
                            if (country != null) {
                                shouldShow = country.equalsIgnoreCase(loadedContact.getCountry());
                            }
                            break;
                    }
                    
                    if (shouldShow) {
                        HBox contactRow = createContactRow(loadedContact);
                        contactsContainer.getChildren().add(contactRow);
                    }
                }
                
                AnchorPane contentPane = (AnchorPane) scrollPane.getContent();
                contentPane.getChildren().clear();
                contentPane.getChildren().add(contactsContainer);
                contactsContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
                
            } catch (IOException e) {
                showAlert("Error", "Failed to filter contacts: " + e.getMessage());
            }
        }
    }

    private String showCountryInputDialog() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Filter by Country");
        dialog.setHeaderText("Enter Country Name");
        dialog.setContentText("Please enter the country to filter by:");
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return "Country";
        }
        return null;
    }

    @FXML
    private void sortHandleClick(ActionEvent event) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Name", "Name", "Country", "Type");
        dialog.setTitle("Sort Contacts");
        dialog.setHeaderText("Sort Options");
        dialog.setContentText("Choose how to sort contacts:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                Contact currentContact = InformationManage.getContactData(contactsDirectory, contact);
                VBox contactsContainer = new VBox(10);
                contactsContainer.setPadding(new javafx.geometry.Insets(10));
                
                Contact[] contactsArray = new Contact[currentContact.getRelatedContacts().size()];
                int i = 0;
                for (Contact relatedContact : currentContact.getRelatedContacts()) {
                    contactsArray[i++] = InformationManage.getContactData(contactsDirectory, relatedContact);
                }
                
                switch (result.get()) {
                    case "Name":
                        java.util.Arrays.sort(contactsArray, (c1, c2) -> {
                            if (c1 instanceof NaturalPerson && c2 instanceof NaturalPerson) {
                                return ((NaturalPerson)c1).compareTo(c2);
                            } else if (c1 instanceof Company && c2 instanceof Company) {
                                return ((Company)c1).compareTo(c2);
                            }
                            return c1.toString().compareTo(c2.toString());
                        });
                        break;
                    case "Country":
                        java.util.Arrays.sort(contactsArray, (c1, c2) -> {
                            String country1 = c1.getCountry() != null ? c1.getCountry() : "";
                            String country2 = c2.getCountry() != null ? c2.getCountry() : "";
                            return country1.compareTo(country2);
                        });
                        break;
                    case "Type":
                        java.util.Arrays.sort(contactsArray, (c1, c2) -> {
                            if (c1 instanceof NaturalPerson && c2 instanceof Company) return -1;
                            if (c1 instanceof Company && c2 instanceof NaturalPerson) return 1;
                            return 0;
                        });
                        break;
                }
                
                // Add sorted contacts to container
                for (Contact sortedContact : contactsArray) {
                    HBox contactRow = createContactRow(sortedContact);
                    contactsContainer.getChildren().add(contactRow);
                }
                
                AnchorPane contentPane = (AnchorPane) scrollPane.getContent();
                contentPane.getChildren().clear();
                contentPane.getChildren().add(contactsContainer);
                contactsContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
                
            } catch (IOException e) {
                showAlert("Error", "Failed to sort contacts: " + e.getMessage());
            }
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        contact = (Contact) DataHolder.getInstance().getMyData();
        loadContactData();
    }
    
    private void loadContactData() {
        try {
            Contact currentContact = InformationManage.getContactData(contactsDirectory, contact);
            
            VBox contactsContainer = new VBox(10);
            contactsContainer.setPadding(new javafx.geometry.Insets(10));
            
            for (Contact relatedContact : currentContact.getRelatedContacts()) {
                Contact loadedContact = InformationManage.getContactData(contactsDirectory, relatedContact);
                HBox contactRow = createContactRow(loadedContact);
                contactsContainer.getChildren().add(contactRow);
            }
            
            AnchorPane contentPane = (AnchorPane) scrollPane.getContent();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(contactsContainer);
            
            contactsContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
            
        } catch (IOException e) {
            showAlert("Error", "Failed to load contacts: " + e.getMessage());
        }
    }
    
    private HBox createContactRow(Contact contact) {
        HBox row = new HBox(15);
        row.setPadding(new javafx.geometry.Insets(10, 15, 10, 15));
        row.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 5;");
        row.setAlignment(Pos.CENTER_LEFT);
        
        ImageView photoView = new ImageView();
        photoView.setFitHeight(50);
        photoView.setFitWidth(50);
        photoView.setPreserveRatio(true);
        
        if (!contact.getPhotos().isEmpty()) {
            Photo firstPhoto = contact.getPhotos().get(0);
            try {
                File file = new File(firstPhoto.path);
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    photoView.setImage(image);
                } else {
                    photoView.setImage(getDefaultImage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                photoView.setImage(getDefaultImage());
            }
        } else {
            photoView.setImage(getDefaultImage());
        }
        
        VBox detailsBox = new VBox(5);
        
        Label nameLabel = new Label();
        if (contact instanceof NaturalPerson) {
            NaturalPerson np = (NaturalPerson) contact;
            nameLabel.setText(np.getFirstName() + " " + np.getSurname());
        } else if (contact instanceof Company) {
            Company c = (Company) contact;
            nameLabel.setText(c.getName());
        }
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        
        Label phoneLabel = new Label();
        if (!contact.getPhoneNumbers().isEmpty()) {
            PhoneNumber firstPhone = contact.getPhoneNumbers().get(0);
            phoneLabel.setText("📞 " + firstPhone.phoneNumber +
                            (firstPhone.utilization != null ?
                            " (" + firstPhone.utilization + ")" : ""));
        } else {
            phoneLabel.setText("📞 No phone number");
        }
        phoneLabel.setStyle("-fx-font-size: 12;");
        
        Label emailLabel = new Label();
        if (!contact.getEmails().isEmpty()) {
            Email firstEmail = contact.getEmails().get(0);
            emailLabel.setText("✉ " + firstEmail.getAddress() +
                            (firstEmail.label != null ?
                            " (" + firstEmail.label + ")" : ""));
        } else {
            emailLabel.setText("✉ No email");
        }
        emailLabel.setStyle("-fx-font-size: 12;");
        
        detailsBox.getChildren().addAll(nameLabel, phoneLabel, emailLabel);
        
        row.getChildren().addAll(photoView, detailsBox);
        
        return row;
    }
    
    private Image getDefaultImage() {
        try {
            return new Image(getClass().getResourceAsStream("/com/espol/group04/grupo_04/images/default_contact.png"));
        } catch (Exception e) {
            return new ImageView().getImage();
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}