package projectpackage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
/**
 * Pizza Manager Main, creates the initial stage and loads corresponding fxml file.
 * Launches into the initial stage once created and fxml is loaded.
 * @author Thomas Shea, James Artuso
 */

public class PizzaManagerMain extends Application {

    /**
     * Method that creates the stage and loads
     * @param stage, stage that is being created.
     * @throws IOException, throws I/O exceptions
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("PizzaManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 550);
        stage.setTitle("Pizza Manager");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            /**
             * Method to exit all screens if user exits initial screen.
             * @param windowEvent, current window event.
             */
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }

    /**
     * Method that will launch the javafx project after the stage is ready.
     * @param args, string arguements being used.
     */
    public static void main(String[] args) {
        launch();
    }

}