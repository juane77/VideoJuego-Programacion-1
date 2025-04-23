package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaJuegoController {

    @FXML
    private Button finButton;

    @FXML
    public void initialize() {
        finButton.setOnAction(event -> {
            // Lógica para finalizar el juego
            Stage stage = (Stage) finButton.getScene().getWindow();
            try {
                // Imprimir la ruta del archivo FXML para depuración
                System.out.println("Ruta del archivo FXML: " + getClass().getResource("/vistas/VistaFin.fxml"));

                // Cargar la vista Fin
                Parent finRoot = FXMLLoader.load(getClass().getResource("/vistas/VistaFin.fxml"));
                Scene finScene = new Scene(finRoot, 800, 600);
                stage.setScene(finScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
