package projectpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class creates and runs the gui
 * @author Thomas Shea, James Artuso
 */
public class GymManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Gym Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}