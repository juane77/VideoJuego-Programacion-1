package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import src.modelos.Jugador;
import src.modelos.Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InicioController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtGmail;

    @FXML
    private Button btnJugar;

    @FXML
    private void initialize() {
        btnJugar.setOnAction(event -> {
            String nombre = txtNombre.getText().trim();
            File jugadorFile = new File("jugadores/" + nombre + ".bin");

            if (nombre.isEmpty()) return;

            if (!jugadorFile.exists()) {
                txtGmail.setVisible(true);
                txtGmail.setManaged(true);

                String gmail = txtGmail.getText().trim();
                if (!gmail.isEmpty()) {
                    try (FileWriter writer = new FileWriter(jugadorFile)) {
                        writer.write(nombre + ";" + gmail);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Modelo.setJugador(new Jugador(nombre, gmail));
                    cambiarAVistaJuego();
                }
            } else {
                Modelo.setJugador(new Jugador(nombre, "placeholder@email.com"));
                cambiarAVistaJuego();
            }
        });
    }

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
