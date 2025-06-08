package com.espol.group04.grupo_04;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SecondaryController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private ListView<String> phoneList;

    @FXML
    private ListView<String> emailList;

    @FXML
    private ListView<String> relatedList;

    private Contact currentContact;

    public void setContact(Contact contact) {
        this.currentContact = contact;
        updateView();
    }

    private void updateView() {
        if (currentContact == null) return;

        nameLabel.setText(currentContact.getName());
        addressLabel.setText(currentContact.getAddress());

        phoneList.getItems().clear();
        for (PhoneNumber p : currentContact.getPhoneNumbers()) {
            phoneList.getItems().add(p.toString());
        }

        emailList.getItems().clear();
        for (Email e : currentContact.getEmails()) {
            emailList.getItems().add(e.toString());
        }

        relatedList.getItems().clear();
        for (Contact c : currentContact.getRelatedContacts()) {
            relatedList.getItems().add(c.getName());
        }
    }
}
