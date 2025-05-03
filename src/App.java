package src;

import base_datos.GestorBaseDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage ventanaPrincipal;

    @Override
    public void start(Stage primaryStage) {
      
        GestorBaseDatos.inicializarBaseDatos();

        ventanaPrincipal = primaryStage;
        mostrarVista("Splash.fxml");
    }

    public static void mostrarVista(String nombreFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/" + nombreFXML));
            Parent root = loader.load();

            Scene escena = new Scene(root, 800, 600);
            ventanaPrincipal.setScene(escena);
            ventanaPrincipal.setTitle("Juego JavaFX con FXML");
            ventanaPrincipal.show();

            if (nombreFXML.equals("Juego.fxml")) {
                root.requestFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public static void main(String[] args) {
        launch(args);
    }
}