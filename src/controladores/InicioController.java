package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

                    cambiarAVistaJuego();
                }

            } else {
             
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
        } catch (Exception e) {
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