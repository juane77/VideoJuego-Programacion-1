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

/**
 * Controlador para la vista de finalización del juego en caso de victoria.
 * Gestiona las acciones disponibles para el usuario, como volver al menú principal,
 * reiniciar el juego o salir de la aplicación.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class FinController implements Initializable {

    /**
     * Método de inicialización del controlador.
     * Se ejecuta al cargar la vista y reproduce la música de victoria.
     * 
     * @param location URL de localización del recurso.
     * @param resources Recursos internacionales asociados al controlador.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 🎵 Reproducir música de victoria al entrar a la pantalla
        ReproductorMusica.reproducir("/sonidos/musica_victoria.mp3");
    }

    /**
     * Vuelve a la vista del Splash (pantalla inicial).
     * 
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    public void volverAlSplash() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Splash.fxml"));
        Parent root = loader.load();
        Stage stage = getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Vuelve a la vista de inicio del juego.
     * 
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    public void volverAInicio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
        Parent root = loader.load();
        Stage stage = getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Cierra la aplicación.
     */
    @FXML
    public void salir() {
        System.exit(0);
    }

    /**
     * Obtiene la ventana principal (Stage) actualmente activa.
     * 
     * @return La ventana principal activa o {@code null} si no hay ninguna.
     */
    private Stage getStage() {
        return (Stage) javafx.stage.Window.getWindows().stream()
                .filter(window -> window.isShowing())
                .findFirst()
                .map(window -> (Stage) window)
                .orElse(null);
    }
}