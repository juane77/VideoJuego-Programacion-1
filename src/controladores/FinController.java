package src.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.App;

public class FinController {

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnSalir;

    @FXML
    public void initialize() {
        btnReiniciar.setOnAction(e -> App.mostrarVista("Inicio.fxml"));
        btnSalir.setOnAction(e -> System.exit(0));
    }
}
