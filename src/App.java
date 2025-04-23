package src;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista Splash
            Parent splashRoot = FXMLLoader.load(getClass().getResource("/vistas/VistaSplash.fxml"));
            Scene splashScene = new Scene(splashRoot, 800, 600);
            primaryStage.setScene(splashScene);
            primaryStage.setTitle("Juego con JavaFX");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
