package gui;/**
 * Created by Carlo on 25/12/2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
