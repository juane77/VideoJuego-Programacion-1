package controladores;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SplashController {

    @FXML
    private Button btnContinuar;

    @FXML
    private Button btnTop10;

    @FXML
    private void initialize() {

        btnContinuar.setOnAction(this::cambiarAVistaInicio);
    }

    private void cambiarAVistaInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnContinuar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirTop10(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Top10.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Top 10 Mejores Tiempos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
