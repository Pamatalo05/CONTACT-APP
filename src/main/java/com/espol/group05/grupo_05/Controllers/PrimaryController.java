package com.espol.group05.grupo_05.Controllers;

import java.io.IOException;

import com.espol.group05.grupo_05.Contacts.Contact;
import com.espol.group05.grupo_05.Contacts.ContactManage;
import com.espol.group05.grupo_05.Utilities.DataHolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button LogInButton;

    @FXML
    private TextField passWordField;

    @FXML
    private TextField userNameField;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        if (LogInButton == null || passWordField == null ||
            userNameField == null || errorLabel == null) {
            throw new IllegalStateException("FXML fields not properly injected!");
        }
        errorLabel.setVisible(false);
    }

    @FXML
    void handleClick(ActionEvent event) throws IOException {
        errorLabel.setVisible(false);
        String user = userNameField.getText().trim();
        String password = passWordField.getText().trim();
        if(user==null || password==null){showError("Any field can not be empty");}
        if(ContactManage.validateCredentials(user, password) instanceof Contact){
            Contact contact = ContactManage.validateCredentials(user, password);
            DataHolder.getInstance().setMyData(contact);
            Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/espol/group04/grupo_04/secondary.fxml"));
            Scene secondScene = new Scene(secondRoot);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(secondScene);
            window.show();
        }
        else{
            showError("Invalid credentials");
        }
    }

    @FXML
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

}
