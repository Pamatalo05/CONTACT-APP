package com.espol.group05.grupo_05.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.espol.group05.grupo_05.Classes.SocialMedia;
import com.espol.group05.grupo_05.Contacts.Company;
import com.espol.group05.grupo_05.Contacts.Contact;
import com.espol.group05.grupo_05.Contacts.TypeCompany;
import com.espol.group05.grupo_05.Utilities.DataHolder;
import com.espol.group05.grupo_05.Utilities.InformationManage;
import com.espol.group05.grupo_05.Classes.*;
import java.io.IOException;
import javafx.event.ActionEvent;

public class QuinaryController {

    private Company newCompany;
    private String contactsDirectory = "path/to/contacts/directory";

    @FXML private TextField companyNameField;
    @FXML private ComboBox<String> companyTypeComboBox;
    @FXML private VBox phonesContainer;
    @FXML private VBox addressesContainer;
    @FXML private VBox socialMediaContainer;
    @FXML private VBox emailsContainer;
    @FXML private VBox photosContainer;
    @FXML private VBox datesContainer;
    @FXML private Button exitButton;

    @FXML
    public void initialize() {
        newCompany = new Company(new String[]{"C", "", ""});
        companyTypeComboBox.getItems().addAll(
            "Technology", "Finance", "Healthcare",
            "Retail", "Education", "Other"
        );
        setupContainers();
    }

    private void setupContainers() {
        String style = "-fx-padding: 5; -fx-spacing: 5;";
        phonesContainer.setStyle(style);
        addressesContainer.setStyle(style);
        socialMediaContainer.setStyle(style);
        emailsContainer.setStyle(style);
        photosContainer.setStyle(style);
        datesContainer.setStyle(style);
    }

    @FXML
    private void handleAddPhone(ActionEvent event) {
        showInputDialog("Add Phone", new String[]{"Type", "Number"},
            result -> {
                if (result != null) {
                    PhoneNumber phone = new PhoneNumber(new String[]{result[0], result[1]});
                    newCompany.getPhoneNumbers().add(phone);
                    addItemToContainer(phonesContainer, phone.phoneNumber + " (" + phone.utilization + ")");
                }
                return null;
            });
    }

    @FXML
    private void handleAddAddress(ActionEvent event) {
        showInputDialog("Add Address", new String[]{"Type", "Street", "City", "Country"},
            result -> {
                if (result != null) {
                    Location location = new Location(result);
                    newCompany.getLocations().add(location);
                    addItemToContainer(addressesContainer, String.join(", ", result));
                }
                return null;
            });
    }

    @FXML
    private void handleAddSocialMedia(ActionEvent event) {
        showInputDialog("Add Social Media", new String[]{"Platform", "Username"},
            result -> {
                if (result != null) {
                    SocialMedia social = new SocialMedia(new String[]{result[0], result[1]});
                    newCompany.getSocialMediaAccounts().add(social);
                    addItemToContainer(socialMediaContainer, social.getUserName() + ": " + social.getLinkProfile());
                }
                return null;
            });
    }

    @FXML
    private void handleAddEmail(ActionEvent event) {
        showInputDialog("Add Email", new String[]{"Type", "Address"},
            result -> {
                if (result != null) {
                    Email email = new Email(new String[]{result[0], result[1]});
                    newCompany.getEmails().add(email);
                    addItemToContainer(emailsContainer, email.getAddress() + " (" + email.label + ")");
                }
                return null;
            });
    }

    @FXML
    private void handleAddPhoto(ActionEvent event) {
        showInputDialog("Add Photo", new String[]{"Description", "Path"},
            result -> {
                if (result != null) {
                    Photo photo = new Photo(new String[]{result[0], result[1]});
                    newCompany.getPhotos().add(photo);
                    addItemToContainer(photosContainer, photo.name);
                }
                return null;
            });
    }

    @FXML
    private void handleAddImportantDate(ActionEvent event) {
        showInputDialog("Add Important Date", new String[]{"Event", "Date"},
            result -> {
                if (result != null) {
                    ImportantDate date = new ImportantDate(new String[]{result[0], result[1]});
                    newCompany.getImportantDates().add(date);
                    addItemToContainer(datesContainer, date.name + ": " + date.date);
                }
                return null;
            });
    }

    @FXML
    private void handleExit() {
        try {
            newCompany.name = companyNameField.getText();
            if (companyTypeComboBox.getValue() != null) {
                newCompany.typeCompany = new TypeCompany(companyTypeComboBox.getValue());
            }

            Contact currentUser = (Contact) DataHolder.getInstance().getMyData();
            currentUser.getRelatedContacts().add(newCompany);
            
            InformationManage.saveContactData(contactsDirectory, currentUser.getUser(), currentUser);
            InformationManage.saveContactData(contactsDirectory, newCompany.getUser(), newCompany);
            
            ((Stage) exitButton.getScene().getWindow()).close();
        } catch (IOException e) {
            showAlert("Error", "Failed to save company: " + e.getMessage());
        }
    }

    private void showInputDialog(String title, String[] fields, Callback<String[], Void> handler) {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle(title);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        TextField[] textFields = new TextField[fields.length];
        for (int i = 0; i < fields.length; i++) {
            grid.add(new Label(fields[i]), 0, i);
            textFields[i] = new TextField();
            grid.add(textFields[i], 1, i);
        }
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String[] results = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    results[i] = textFields[i].getText();
                }
                return results;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(handler::call);
    }

    private void addItemToContainer(VBox container, String text) {
        Label label = new Label(text);
        container.getChildren().add(label);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    interface Callback<T, R> {
        R call(T t);
    }
}