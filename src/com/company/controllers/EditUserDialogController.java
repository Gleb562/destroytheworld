package com.company.controllers;

import com.company.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.company.controllers.conn.statmt;

public class EditUserDialogController {
    @FXML
    private TextField txtfieldEditUsername;
    @FXML
    private TextField txtfieldEditUserPass;
    @FXML
    private TextField txtfieldEditAccess;

    private Person person;
    conn Conn = new conn();

    public void setContract(Person person) {
        this.person = person;
        txtfieldEditUsername.setText(person.getUsername());
        txtfieldEditUserPass.setText(String.valueOf(person.getPassword()));
        txtfieldEditAccess.setText(String.valueOf(person.getAccessLevel()));
    }

    public void actionSaveContract(ActionEvent actionEvent) throws SQLException {
        String EditName = "\"" + txtfieldEditUsername.getText() + "\"";
        String EditPassword = "\"" + txtfieldEditUserPass.getText() + "\"";
        String EditAccess = "\"" + txtfieldEditAccess.getText() + "\"";
        String EditId = "\"" + person.getId() + "\"";
        statmt.execute("UPDATE user SET username =" + EditName + ",password =" + EditPassword + ",access_level =" + EditAccess + "WHERE id =" + EditId+"; ");
        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
