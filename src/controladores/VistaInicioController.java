package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaInicioController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField gmailField;

    @FXML
    private Button jugarButton;

    @FXML
    public void initialize() {
        jugarButton.setOnAction(event -> {
            String nombre = nombreField.getText();
            String gmail = gmailField.getText();
            // Lógica para iniciar el juego
            Stage stage = (Stage) jugarButton.getScene().getWindow();
            try {
                // Imprimir la ruta del archivo FXML para depuración
                System.out.println("Ruta del archivo FXML: " + getClass().getResource("/vistas/VistaJuego.fxml"));

                // Cargar la vista Juego
                Parent juegoRoot = FXMLLoader.load(getClass().getResource("/vistas/VistaJuego.fxml"));
                Scene juegoScene = new Scene(juegoRoot, 800, 600);
                stage.setScene(juegoScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
