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

    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField socialField;
    @FXML private TextField dateField;
    @FXML private TextField photoField;
    @FXML private TextArea contactDisplay;
    @FXML private ListView<String> phoneList;
    @FXML private ListView<String> emailList;
    
    private ObservableList<String> phones = FXCollections.observableArrayList();
    private DoubleCircularList<Contact> contacts = new DoubleCircularList<>();
    private DoubleCircularNodo<Contact> current = null;
    private ObservableList<String> emails = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        emailList.setItems(emails);
        phoneList.setItems(phones);
    }

    @FXML
    private void handleAddPhone() {
        String phone = phoneField.getText().trim();
        if (!phone.isEmpty()) {
            phones.add(phone);
            phoneField.clear();
        }
    }
    
    @FXML
    private void handleNext() {
        if (current != null) {
            current = current.getNext();
            displayContact(current.getData());
        }
    }

    @FXML
    private void handlePrevious() {
        if (current != null) {
            current = current.getPrevious();
            displayContact(current.getData());
        }
    }

    @FXML
    private void handleAddContact() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        String social = socialField.getText().trim();
        String date = dateField.getText().trim();
        String photo = photoField.getText().trim();

        if (name.isEmpty()) {
            showAlert("Validation Error", "Name cannot be empty.");
            return;
        }

        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                showAlert("Duplicate Contact", "A contact with the name '" + name + "' already exists.");
                return;
            }
        }

        Contact newContact = new Contact(name, address);
        for (String p : phones) newContact.addPhoneNumber(new PhoneNumber(p, "mobile"));
        for (String e : emails) newContact.addEmail(new Email(e, "personal"));
        if (!social.isEmpty()) newContact.addSocialMedia(new SocialMedia("unspecified", social));
        if (!date.isEmpty()) newContact.addImportantDate(new ImportantDate("unspecified", date));
        if (!photo.isEmpty()) newContact.addPhoto(photo);

        contacts.addLast(newContact);
        if (current == null) current = contacts.getHead();
        displayContact(current.getData());
        clearFields();
    }
    
    @FXML
    private void handleDeleteContact() {
        if (current != null) {
            Contact toDelete = current.getData();
            contacts.delete(toDelete);
            current = contacts.getHead();
            if (current != null) displayContact(current.getData());
            else contactDisplay.setText("No contacts available.");
        }
    }
    
    @FXML
    private void handleAddEmail() {
        String email = emailField.getText().trim();
        if (!email.isEmpty()) {
          emails.add(email);
          emailField.clear();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleEditContact() {
        if (current == null) return;

        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        String social = socialField.getText().trim();
        String date = dateField.getText().trim();
        String photo = photoField.getText().trim();

        Contact edited = new Contact(name, address);
        for (String p : phones) edited.addPhoneNumber(new PhoneNumber(p, "mobile"));
        if (!email.isEmpty()) edited.addEmail(new Email(email, "personal"));
        if (!social.isEmpty()) edited.addSocialMedia(new SocialMedia("unspecified", social));
        if (!date.isEmpty()) edited.addImportantDate(new ImportantDate("unspecified", date));
        if (!photo.isEmpty()) edited.addPhoto(photo);

        contacts.delete(current.getData());
        contacts.addLast(edited);
        current = contacts.getHead();
        displayContact(current.getData());
        clearFields();
    }

    @FXML
    private void handleShowDetails() {
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

    @FXML
    private void handleLoadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            Contact contact = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals("#CONTACT")) {
                    contact = new Contact("", "");
                } else if (line.startsWith("Name:")) {
                    contact.setName(line.substring(5));
                } else if (line.startsWith("Address:")) {
                    contact.setAddress(line.substring(8));
                } else if (line.startsWith("Phones:")) {
                    String[] parts = line.substring(7).split(";");
                    for (String part : parts) {
                        if (part.contains(":")) {
                            String[] pair = part.split(":");
                            contact.addPhoneNumber(new PhoneNumber(pair[1], pair[0]));
                        }
                    }
                } else if (line.startsWith("Emails:")) {
                    String[] parts = line.substring(7).split(";");
                    for (String part : parts) {
                        if (part.contains(":")) {
                            String[] pair = part.split(":");
                            contact.addEmail(new Email(pair[1], pair[0]));
                        }
                    }
                } else if (line.startsWith("Socials:")) {
                    String[] parts = line.substring(8).split(";");
                    for (String part : parts) {
                        if (part.contains(":")) {
                            String[] pair = part.split(":");
                            contact.addSocialMedia(new SocialMedia(pair[0], pair[1]));
                        }
                    }
                } else if (line.startsWith("Dates:")) {
                    String[] parts = line.substring(6).split(";");
                    for (String part : parts) {
                        if (part.contains(":")) {
                            String[] pair = part.split(":");
                            contact.addImportantDate(new ImportantDate(pair[0], pair[1]));
                        }
                    }
                } else if (line.startsWith("Photos:")) {
                    String[] parts = line.substring(7).split(";");
                    for (String p : parts) {
                        if (!p.isEmpty()) {
                            contact.addPhoto(p);
                        }
                    }
                } else if (line.equals("---")) {
                    contacts.addLast(contact);
                    if (current == null) current = contacts.getHead();
                }
            }
            if (current != null) displayContact(current.getData());
            System.out.println("Contacts loaded.");
        } catch (IOException e) {
            e.printStackTrace();
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

        contactDisplay.setText(sb.toString());
    }

    private void clearFields() {
        nameField.clear();
        addressField.clear();
        phoneField.clear();
        emailField.clear();
        socialField.clear();
        dateField.clear();
        photoField.clear();
        phones.clear();
    }
    
    @FXML
    private void handleSaveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("contacts.txt"))) {
            for (Contact contact : contacts) {
                writer.println("#CONTACT");
                writer.println("Name:" + contact.getName());
                writer.println("Address:" + contact.getAddress());

                StringBuilder phones = new StringBuilder("Phones:");
                for (PhoneNumber p : contact.getPhoneNumbers()) {
                    phones.append(p.type).append(":").append(p.number).append(";");
                }
                writer.println(phones);

                StringBuilder emails = new StringBuilder("Emails:");
                for (Email e : contact.getEmails()) {
                    emails.append(e.label).append(":").append(e.address).append(";");
                }
                writer.println(emails);

                StringBuilder socials = new StringBuilder("Socials:");
                for (SocialMedia s : contact.getSocialMediaAccounts()) {
                    socials.append(s.platform).append(":").append(s.username).append(";");
                }
                writer.println(socials);

                StringBuilder dates = new StringBuilder("Dates:");
                for (ImportantDate d : contact.getImportantDates()) {
                    dates.append(d.label).append(":").append(d.date).append(";");
                }
                writer.println(dates);

                StringBuilder photos = new StringBuilder("Photos:");
                for (String p : contact.getPhotos()) {
                    photos.append(p).append(";");
                }
                writer.println(photos);

                writer.println("---");
            }
            System.out.println("Contacts saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

