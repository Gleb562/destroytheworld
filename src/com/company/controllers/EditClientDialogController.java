package com.company.controllers;

import com.company.objects.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.company.controllers.conn.statmt;

public class EditClientDialogController {
    @FXML
    private TextField txtfieldEditClientName;
    @FXML
    private TextField txtfieldEditClientPhone;
    @FXML
    private TextField txtfieldEditClientEmail;

    private Client client;
    conn Conn = new conn();

    public void setClient(Client client) {
        this.client = client;
        txtfieldEditClientName.setText(client.getClientName());
        txtfieldEditClientPhone.setText(client.getPhone());
        txtfieldEditClientEmail.setText(client.getEmail());
    }

    public void actionSaveClient(ActionEvent actionEvent) throws SQLException {
        String EditName = "\"" + txtfieldEditClientName.getText() + "\"";
        String EditPhone = "\"" + txtfieldEditClientPhone.getText() + "\"";
        String EditEmail = "\"" + txtfieldEditClientEmail.getText() + "\"";
        String EditId = "\"" + client.getId() + "\"";
        statmt.execute("UPDATE clients SET client_name =" + EditName + ",phone =" + EditPhone + ",email =" + EditEmail + "WHERE id =" + EditId+"; ");
        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
