package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import src.modelos.Jugador;
import src.modelos.Modelo;
import util.ReproductorMusica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controlador para la vista de inicio del juego.
 * Permite al usuario ingresar su nombre, seleccionar un escenario y comenzar el juego.
 * También gestiona la creación de nuevos jugadores y la configuración inicial.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class InicioController {

    /**
     * Campo de texto para ingresar el nombre del jugador.
     */
    @FXML private TextField txtNombre;

    /**
     * Campo de texto para ingresar el correo electrónico del jugador.
     * Este campo solo se muestra si el jugador no existe.
     */
    @FXML private TextField txtGmail;

    /**
     * Botón para comenzar el juego.
     */
    @FXML private Button btnJugar;

    /**
     * Botón para seleccionar el escenario 1.
     */
    @FXML private Button btnEscenario1;

    /**
     * Botón para seleccionar el escenario 2.
     */
    @FXML private Button btnEscenario2;

    /**
     * Botón para seleccionar el escenario 3.
     */
    @FXML private Button btnEscenario3;

    /**
     * Botón para seleccionar el escenario 4.
     */
    @FXML private Button btnEscenario4;

    /**
     * Método de inicialización del controlador.
     * Configura la música de fondo, establece el escenario por defecto y configura los botones.
     */
    @FXML
    private void initialize() {
        // Reproducir música de fondo del menú
        ReproductorMusica.reproducir("/sonidos/musica_menu.mp3");

        // Establecer el escenario por defecto
        Modelo.setRutaEscenario("escenarios/escenario1.txt");

        // Configurar los botones para seleccionar escenarios
        btnEscenario1.setOnAction(e -> Modelo.setRutaEscenario("escenarios/escenario1.txt"));
        btnEscenario2.setOnAction(e -> Modelo.setRutaEscenario("escenarios/escenario2.txt"));
        btnEscenario3.setOnAction(e -> Modelo.setRutaEscenario("escenarios/escenario3.txt"));
        btnEscenario4.setOnAction(e -> Modelo.setRutaEscenario("escenarios/escenario4.txt"));

        // Configurar el botón "Jugar"
        btnJugar.setOnAction(event -> {
            String nombre = txtNombre.getText().trim();
            File jugadorFile = new File("jugadores/" + nombre + ".bin");

            // Si el nombre está vacío, no hacer nada
            if (nombre.isEmpty()) return;

            // Si el jugador no existe, solicitar el correo electrónico
            if (!jugadorFile.exists()) {
                txtGmail.setVisible(true);
                txtGmail.setManaged(true);

                String gmail = txtGmail.getText().trim();
                if (!gmail.isEmpty()) {
                    // Guardar los datos del nuevo jugador en un archivo
                    try (FileWriter writer = new FileWriter(jugadorFile)) {
                        writer.write(nombre + ";" + gmail);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Establecer el jugador actual y cambiar a la vista del juego
                    Modelo.setJugador(new Jugador(nombre, gmail));
                    cambiarAVistaJuego();
                }
            } else {
                // Si el jugador ya existe, cargarlo con un correo electrónico placeholder
                Modelo.setJugador(new Jugador(nombre, "placeholder@email.com"));
                cambiarAVistaJuego();
            }
        });
    }

    /**
     * Cambia a la vista del juego.
     * Carga el archivo FXML correspondiente y actualiza la escena.
     */
    private void cambiarAVistaJuego() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Juego.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnJugar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}