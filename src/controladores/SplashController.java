package src.controladores;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import src.App;

public class SplashController {

    @FXML
    private StackPane root;

    @FXML
    public void initialize() {
        PauseTransition pausa = new PauseTransition(Duration.seconds(2.5));
        pausa.setOnFinished(e -> App.mostrarVista("Inicio.fxml"));
        pausa.play();

        root.setOnKeyPressed(this::irInicio);
        root.setOnMouseClicked(this::irInicio);
        root.requestFocus();
    }

    private void irInicio(KeyEvent e) {
        App.mostrarVista("Inicio.fxml");
    }

    private void irInicio(MouseEvent e) {
        App.mostrarVista("Inicio.fxml");
    }
}
