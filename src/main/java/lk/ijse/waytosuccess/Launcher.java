package lk.ijse.waytosuccess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
