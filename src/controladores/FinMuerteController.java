package controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinMuerteController implements Initializable {

    @FXML
    private Button botonVolver;

    @FXML
    private Button botonSalir;

    @FXML
    private ImageView fondo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fondo.setImage(new Image(getClass().getResourceAsStream("/imagenes/fondo_inicio.png")));
        botonVolver.setOnMouseClicked(this::volverAlInicio);
        botonSalir.setOnMouseClicked(this::salirDelJuego);
    }

    private void volverAlInicio(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) botonVolver.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salirDelJuego(MouseEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }
}

