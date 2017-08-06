package com.company.controllers;


import com.company.objects.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.company.controllers.conn.statmt;

public class EditDialogController {
        @FXML
        private Button btnOk1;
        @FXML
        private Button btnCancel;
        @FXML
        private TextField txtfieldEditSerName;
        @FXML
        private TextField txtfieldEditPrice;

        private User user;
        conn Conn = new conn();

        public void setUser(User user) {
            this.user = user;
            txtfieldEditSerName.setText(user.getPname());
            txtfieldEditPrice.setText(String.valueOf(user.getPrice()));
        }

        public void actionClose(ActionEvent actionEvent) {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.hide();
        }


        public void actionSave(ActionEvent actionEvent) throws NumberFormatException, SQLException {
            String EditPname = "\"" + txtfieldEditSerName.getText() + "\"";
            String EditPrice = "\"" + txtfieldEditPrice.getText() + "\"";
            String EditId = "\"" + user.getId() + "\"";
            statmt.execute("UPDATE prices SET pname =" + EditPname + ",price =" + EditPrice + "WHERE id =" + EditId+"; ");
            actionClose(actionEvent);
        }
}


