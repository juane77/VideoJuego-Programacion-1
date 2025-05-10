package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ReproductorMusica {
    private static MediaPlayer mediaPlayer;
    private static String musicaActual;

    public static void reproducir(String ruta) {
        // Si la misma música ya se está reproduciendo, no hacemos nada
        if (mediaPlayer != null &&
            musicaActual != null &&
            musicaActual.equals(ruta) &&
            mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        // Si hay otra música sonando, la detenemos
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // Reproducimos la nueva música
        Media media = new Media(ReproductorMusica.class.getResource(ruta).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        musicaActual = ruta;
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void detener() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
            musicaActual = null;
        }
    }
}
