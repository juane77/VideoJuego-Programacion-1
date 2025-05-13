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

/**
 * Controlador para la vista de Splash (pantalla inicial).
 * Se encarga de reproducir un video de fondo, música de menú y gestionar las transiciones
 * hacia otras vistas como el menú principal o el Top 10.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class SplashController {

    /**
     * Botón para continuar hacia la vista de inicio.
     */
    @FXML
    private Button btnContinuar;

    /**
     * Componente para mostrar el video de fondo.
     */
    @FXML
    private MediaView mediaView;

    /**
     * Método de inicialización del controlador.
     * Reproduce el video de fondo y la música del menú, y configura el botón para cambiar de vista.
     */
    @FXML
    public void initialize() {
        // Reproducir el video de fondo
        reproducirVideoFondo();

        // Reproducir la música del menú
        ReproductorMusica.reproducir("/sonidos/musica_menu.mp3");

        // Configurar el botón para cambiar a la vista de inicio
        btnContinuar.setOnAction(event -> cambiarAVistaInicio());
    }

    /**
     * Reproduce un video de fondo en bucle.
     * Si no se encuentra el archivo del video, muestra un mensaje de error en la consola.
     */
    private void reproducirVideoFondo() {
        try {
            // Obtener la URL del video
            URL mediaUrl = getClass().getResource("/videos/fondo.mp4");
            if (mediaUrl == null) {
                System.err.println("No se encontró el video en /videos/fondo.mp4");
                return;
            }

            // Configurar el reproductor de video
            Media media = new Media(mediaUrl.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproducir en bucle
            mediaPlayer.setMute(true); // Silenciar el video si hay música separada
            mediaPlayer.play();

            // Asignar el reproductor al componente MediaView
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción en caso de error
        }
    }

    /**
     * Cambia a la vista de inicio (menú principal).
     * 
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    private void cambiarAVistaInicio() {
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) btnContinuar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir la traza de la excepción en caso de error
        }
    }

    /**
     * Abre una nueva ventana con la vista del Top 10 de jugadores.
     * 
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    public void abrirTop10() throws IOException {
        // Cargar la vista del Top 10
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Top10.fxml"));
        Parent root = loader.load();

        // Crear una nueva ventana para mostrar el Top 10
        Stage stage = new Stage();
        stage.setTitle("Top 10 Mejores Tiempos");
        stage.setScene(new Scene(root));
        stage.show();
    }
}