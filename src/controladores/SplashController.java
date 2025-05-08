package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class SplashController {

    @FXML
    private Button btnContinuar;

    @FXML
    public void initialize() {
        btnContinuar.setOnAction(event -> cambiarAVistaInicio());
    }

    private void cambiarAVistaInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnContinuar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirTop10() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Top10.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Top 10 Mejores Tiempos");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
