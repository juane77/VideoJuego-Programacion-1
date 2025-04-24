package src.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import src.App;
import src.modelos.Jugador;
import src.modelos.Modelo;

public class InicioController {

    @FXML
    private VBox root;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoGmail;

    @FXML
    private Button btnJugar;

    private final Modelo modelo = new Modelo();

    @FXML
    public void initialize() {
        btnJugar.setOnAction(e -> {
            String nombre = campoNombre.getText().trim();
            String gmail = campoGmail.getText().trim();

            if (nombre.isEmpty() || gmail.isEmpty()) {
                mostrarAlerta("Debes rellenar todos los campos");
                return;
            }

            Jugador jugador;
            if (modelo.existeJugador(nombre)) {
                try {
                    jugador = modelo.cargarJugador(nombre);
                } catch (Exception ex) {
                    mostrarAlerta("Error al cargar jugador existente");
                    return;
                }
            } else {
                jugador = new Jugador(nombre, gmail);
                try {
                    modelo.guardarJugador(jugador);
                } catch (Exception ex) {
                    mostrarAlerta("No se pudo guardar el nuevo jugador");
                    return;
                }
            }

            App.mostrarVista("Juego.fxml");
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
