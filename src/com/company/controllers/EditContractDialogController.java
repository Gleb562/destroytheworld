package com.company.controllers;

import com.company.objects.Contract;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.company.controllers.conn.statmt;

public class EditContractDialogController {
    @FXML
    private TextField txtfieldEditContractName;
    @FXML
    private TextField txtfieldEditContractProfit;
    @FXML
    private TextField txtfieldEditContractDate;

    private Contract contract;
    conn Conn = new conn();

    public void setContract(Contract contract) {
        this.contract = contract;
        txtfieldEditContractName.setText(contract.getClientName());
        txtfieldEditContractProfit.setText(String.valueOf(contract.getProfit()));
        txtfieldEditContractDate.setText(contract.getDate());
    }

    public void actionSaveContract(ActionEvent actionEvent) throws SQLException {
        String EditName = "\"" + txtfieldEditContractName.getText() + "\"";
        String EditProfit = "\"" + txtfieldEditContractProfit.getText() + "\"";
        String EditDate = "\"" + txtfieldEditContractDate.getText() + "\"";
        String EditId = "\"" + contract.getId() + "\"";
        statmt.execute("UPDATE contracts SET contract_name =" + EditName + ",profit =" + EditProfit + ",date =" + EditDate + "WHERE id =" + EditId+"; ");
        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
