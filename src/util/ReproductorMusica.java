package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ReproductorMusica {
    private static MediaPlayer mediaPlayer;

    public static void reproducir(String ruta) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(ReproductorMusica.class.getResource(ruta).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir en bucle
        mediaPlayer.play();
    }

    public static void parar() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}

