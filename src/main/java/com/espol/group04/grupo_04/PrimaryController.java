package com.espol.group04.grupo_04;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.espol.group04.grupo_04.Utilities.DoubleCircularList;
import com.espol.group04.grupo_04.Utilities.DoubleCircularNodo;

import java.io.*;

public class PrimaryController {

    @FXML private TextField nameField, addressField, phoneField, emailField, socialField, dateField, photoField;
    @FXML private ListView<String> phoneList, emailList, socialList, dateList, photoList;
    @FXML private ComboBox<String> relatedComboBox;
    @FXML private TextArea contactDisplay;

    private ObservableList<String> phones = FXCollections.observableArrayList();
    private ObservableList<String> emails = FXCollections.observableArrayList();
    private ObservableList<String> socials = FXCollections.observableArrayList();
    private ObservableList<String> dates = FXCollections.observableArrayList();
    private ObservableList<String> photos = FXCollections.observableArrayList();
    private ObservableList<String> relatedNames = FXCollections.observableArrayList();

    private DoubleCircularList<Contact> contacts = new DoubleCircularList<>();
    private DoubleCircularNodo<Contact> current = null;

    @FXML
    private void initialize() {
        phoneList.setItems(phones);
        emailList.setItems(emails);
        socialList.setItems(socials);
        dateList.setItems(dates);
        photoList.setItems(photos);
        relatedComboBox.setItems(relatedNames);
    }

    @FXML private void handleNext() {
        if (current != null) {
            current = current.getNext();
            displayContact(current.getData());
        }
    }

    @FXML private void handlePrevious() {
        if (current != null) {
            current = current.getPrevious();
            displayContact(current.getData());
        }
    }

    @FXML private void handleAddPhone() {
        String phone = phoneField.getText().trim();
        if (!phone.isEmpty()) {
            phones.add(phone);
            phoneField.clear();
        }
    }

    @FXML private void handleAddEmail() {
        String email = emailField.getText().trim();
        if (!email.isEmpty()) {
            emails.add(email);
            emailField.clear();
        }
    }

    @FXML private void handleAddSocial() {
        String social = socialField.getText().trim();
        if (!social.isEmpty()) {
            socials.add(social);
            socialField.clear();
        }
    }

    @FXML private void handleAddDate() {
        String date = dateField.getText().trim();
        if (!date.isEmpty()) {
            dates.add(date);
            dateField.clear();
        }
    }

    @FXML private void handleAddPhoto() {
        String photo = photoField.getText().trim();
        if (!photo.isEmpty()) {
            photos.add(photo);
            photoField.clear();
        }
    }

    @FXML private void handleAddRelation() {
        if (current == null) return;
        String name = relatedComboBox.getValue();
        if (name == null || name.isEmpty()) return;

        for (Contact c : contacts) {
            if (c.getName().equals(name) && c != current.getData()) {
                current.getData().addRelatedContact(c);
                showAlert("Relation added", name + " was related to " + current.getData().getName());
                return;
            }
        }
    }

    @FXML private void handleAddContact() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty()) {
            showAlert("Validation Error", "Name cannot be empty.");
            return;
        }

        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                showAlert("Duplicate Contact", "A contact with this name already exists.");
                return;
            }
        }

        Contact newContact = new Contact(name, address);
        for (String p : phones) newContact.addPhoneNumber(new PhoneNumber(p, "mobile"));
        for (String e : emails) newContact.addEmail(new Email(e, "personal"));
        for (String s : socials) newContact.addSocialMedia(new SocialMedia("unspecified", s));
        for (String d : dates) newContact.addImportantDate(new ImportantDate("unspecified", d));
        for (String ph : photos) newContact.addPhoto(ph);

        contacts.addLast(newContact);
        relatedNames.add(name);
        if (current == null) current = contacts.getHead();
        displayContact(current.getData());
        clearFields();
    }

    @FXML private void handleDeleteContact() {
        if (current != null) {
            Contact toDelete = current.getData();
            contacts.delete(toDelete);
            relatedNames.remove(toDelete.getName());
            current = contacts.getHead();
            if (current != null) displayContact(current.getData());
            else contactDisplay.setText("No contacts available.");
        }
    }

    @FXML private void handleShowDetails() {
        if (current != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/group04/grupo_04/secondary.fxml"));
                Parent root = loader.load();
                SecondaryController controller = loader.getController();
                controller.setContact(current.getData());

                Stage stage = new Stage();
                stage.setTitle("Contact Details");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void displayContact(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(contact.getName()).append("\n");
        sb.append("Address: ").append(contact.getAddress()).append("\n");

        sb.append("Phone(s):\n");
        for (PhoneNumber p : contact.getPhoneNumbers()) sb.append("- ").append(p).append("\n");

        sb.append("Email(s):\n");
        for (Email e : contact.getEmails()) sb.append("- ").append(e).append("\n");

        sb.append("Social Media:\n");
        for (SocialMedia sm : contact.getSocialMediaAccounts()) sb.append("- ").append(sm).append("\n");

        sb.append("Important Dates:\n");
        for (ImportantDate d : contact.getImportantDates()) sb.append("- ").append(d).append("\n");

        sb.append("Photos:\n");
        for (String photo : contact.getPhotos()) sb.append("- ").append(photo).append("\n");

        sb.append("Related Contacts:\n");
        for (Contact rc : contact.getRelatedContacts()) sb.append("- ").append(rc.getName()).append("\n");

        contactDisplay.setText(sb.toString());
    }

    private void clearFields() {
        nameField.clear(); addressField.clear(); phoneField.clear(); emailField.clear();
        socialField.clear(); dateField.clear(); photoField.clear();
        phones.clear(); emails.clear(); socials.clear(); dates.clear(); photos.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setHeaderText(null); alert.setContentText(content);
        alert.showAndWait();
    }
}
