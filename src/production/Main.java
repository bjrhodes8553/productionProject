package production;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the JavaFX program. It is required to run a program in JavaFX Start function will
 * start the JavaFX application. The stage of the application is set in the parameter. The name of
 * the application stage is primaryStage. It uses functions such as set title and set scene to
 * create the stage. The final function (.show) is used to display the stage to the user.
 */
public class Main extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Production Task: ");
    primaryStage.setScene(new Scene(root, 400, 300));
    primaryStage.show();
  }

  /** Launches the application. */
  public static void main(String[] args) {

    launch(args);
  } // end main
} // end class
