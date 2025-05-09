package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import util.ReproductorMusica;

import java.io.IOException;
import java.net.URL;

public class SplashController {

    @FXML
    private Button btnContinuar;

    @FXML
    private MediaView mediaView;

    @FXML
    public void initialize() {
        reproducirVideoFondo();
        ReproductorMusica.reproducir("/sonidos/musica_menu.mp3");

        btnContinuar.setOnAction(event -> cambiarAVistaInicio());
    }

    private void reproducirVideoFondo() {
        try {
            URL mediaUrl = getClass().getResource("/videos/fondo.mp4");
            if (mediaUrl == null) {
                System.err.println("No se encontró el video en /videos/fondo.mp4");
                return;
            }

            Media media = new Media(mediaUrl.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setMute(true); // Silenciar si ya hay música separada
            mediaPlayer.play();

            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
