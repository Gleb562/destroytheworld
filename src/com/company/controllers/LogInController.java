package com.company.controllers;

import com.company.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.controllers.conn.statmt;

public class LogInController {

    @FXML
    private TextField txtFieldUserName;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Text txtWrongPass;

    private Person person;
    public static ResultSet resSet;
    conn Conn = new conn();

    public void actionSignIn(ActionEvent actionEvent) {
        String username = "\"" + txtFieldUserName.getText() + "\"";
        String password = "\"" + txtFieldPassword.getText() + "\"";
        try {
            resSet = statmt.executeQuery("SELECT * FROM user WHERE username =" + username +";");
        } catch (SQLException e) {
            txtWrongPass.setVisible(true);
        }
        String  correctPassword = null;
        try {
            correctPassword = resSet.getString("password");
        } catch (SQLException e) {
        }
        if(txtFieldPassword.getText().equals(correctPassword)) {
            actionClose(actionEvent);
        }
        else{
            txtWrongPass.setVisible(true);
        }
        try {
            Conn.currentAccessLvl = resSet.getInt("access_level");
        } catch (SQLException e) {

        }
    }

    public void actionRegister(ActionEvent actionEvent) throws SQLException {
        String username = "\"" + txtFieldUserName.getText() + "\"";
        String password = "\"" + txtFieldPassword.getText() + "\"";
        statmt.execute("INSERT INTO user (username, password, access_level) VALUES (" + username +"," + password + "," + "0" + "); ");
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
