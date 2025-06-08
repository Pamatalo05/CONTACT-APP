package com.espol.group04.grupo_04.InterfazGrafica;

import com.espol.group04.grupo_04.Clases.DoubleCircularList;
import com.espol.group04.grupo_04.Clases.DoubleCircularNodo;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField socialField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField photoField;
    @FXML
    private TextArea contactDisplay;

    private DoubleCircularList<Contact> contacts = new DoubleCircularList<>();
    private DoubleCircularNodo<Contact> current = null;

    @FXML
    private void handleAddContact() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String social = socialField.getText().trim();
        String date = dateField.getText().trim();
        String photo = photoField.getText().trim();

        if (name.isEmpty())
            return;

        Contact newContact = new Contact(name, address);
        if (!phone.isEmpty())
            newContact.addPhoneNumber(new PhoneNumber(phone, "mobile"));
        if (!email.isEmpty())
            newContact.addEmail(new Email(email, "personal"));
        if (!social.isEmpty())
            newContact.addSocialMedia(new SocialMedia("unspecified", social));
        if (!date.isEmpty())
            newContact.addImportantDate(new ImportantDate("unspecified", date));
        if (!photo.isEmpty())
            newContact.addPhoto(photo);

        contacts.addLast(newContact);
        if (current == null)
            current = contacts.getHead();
        displayContact(current.getData());
        clearFields();
    }

    @FXML
    private void handleDeleteContact() {
        if (current != null) {
            Contact toDelete = current.getData();
            contacts.delete(toDelete);
            current = contacts.getHead();
            if (current != null)
                displayContact(current.getData());
            else
                contactDisplay.setText("No contacts available.");
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

    private void displayContact(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(contact.getName()).append("\n");
        sb.append("Address: ").append(contact.getAddress()).append("\n");

        sb.append("Phone(s):\n");
        for (PhoneNumber p : contact.getPhoneNumbers())
            sb.append("- ").append(p).append("\n");

        sb.append("Email(s):\n");
        for (Email e : contact.getEmails())
            sb.append("- ").append(e).append("\n");

        sb.append("Social Media:\n");
        for (SocialMedia sm : contact.getSocialMediaAccounts())
            sb.append("- ").append(sm).append("\n");

        sb.append("Important Dates:\n");
        for (ImportantDate d : contact.getImportantDates())
            sb.append("- ").append(d).append("\n");

        sb.append("Photos:\n");
        for (String photo : contact.getPhotos())
            sb.append("- ").append(photo).append("\n");

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
    }
}
