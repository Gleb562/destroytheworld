package com.company.start;


import com.company.controllers.conn;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class db extends Application{
    Stage window;
    Scene scene1,scene2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        conn Conn = new conn();
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        window.setTitle("JavaFX_laba7_Gleb");
        window.setScene(new Scene(root, 850, 500));
        window.setMinHeight(275);
        window.setMinWidth(530);
        window.show();


        /*Parent root1 = FXMLLoader.load(getClass().getResource("../fxml/init.fxml"));
        window.setMinHeight(0);
        window.setMinWidth(0);
        scene1 = new Scene(root1, 365, 250);
        Window parentWindow = primaryStage;
        window.setScene(scene1);*/

    }

    public Stage getWindow() {
        return window;
    }

    public void closeWindow() {
        Platform.exit();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        conn.Conn();
        launch(args);

    }



}




