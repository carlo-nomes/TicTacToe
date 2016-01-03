package gui; /**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 25/12/2015<br/>
 * Time: 12:39<br/>
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class Main extends Application {

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
