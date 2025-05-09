package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import util.ReproductorMusica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinMuerteController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 🎵 Reproducir música de derrota
        ReproductorMusica.reproducir("/sonidos/musica_derrota.mp3");
    }

    @FXML
    public void volverAlSplash() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Splash.fxml"));
        Parent root = loader.load();
        Stage stage = getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void volverAInicio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
        Parent root = loader.load();
        Stage stage = getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void salir() {
        System.exit(0);
    }

    private Stage getStage() {
        return (Stage) javafx.stage.Window.getWindows().stream()
                .filter(window -> window.isShowing())
                .findFirst()
                .map(window -> (Stage) window)
                .orElse(null);
    }
}
