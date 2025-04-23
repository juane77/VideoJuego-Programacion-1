package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaSplashController {

    @FXML
    private Button inicioButton;

    @FXML
    public void initialize() {
        inicioButton.setOnAction(event -> {
            try {
                // Imprimir la ruta del archivo FXML para depuración
                System.out.println("Ruta del archivo FXML: " + getClass().getResource("/vistas/VistaInicio.fxml"));

                // Cargar la vista Inicio
                Stage stage = (Stage) inicioButton.getScene().getWindow();
                Parent inicioRoot = FXMLLoader.load(getClass().getResource("/vistas/VistaInicio.fxml"));
                Scene inicioScene = new Scene(inicioRoot, 800, 600);
                stage.setScene(inicioScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
