package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Clase utilitaria para gestionar la reproducción de música en la aplicación.
 * Permite reproducir música en bucle y detenerla cuando sea necesario.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class ReproductorMusica {

    /**
     * Reproductor de medios utilizado para reproducir la música.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * Ruta de la música que se está reproduciendo actualmente.
     */
    private static String musicaActual;

    /**
     * Reproduce una música específica desde la ruta proporcionada.
     * Si la música ya se está reproduciendo, no hace nada.
     * Si hay otra música sonando, la detiene antes de reproducir la nueva.
     *
     * @param ruta Ruta del archivo de música (relativa al classpath).
     */
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
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproduce en bucle
        mediaPlayer.play(); // Inicia la reproducción
    }

    /**
     * Detiene la reproducción de la música actual.
     * Si no hay música reproduciéndose, no hace nada.
     */
    public static void detener() {
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Detiene la música
            mediaPlayer = null; // Libera el reproductor
            musicaActual = null; // Resetea la música actual
        }
    }
}