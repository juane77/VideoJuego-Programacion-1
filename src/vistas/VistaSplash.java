package src.vistas;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class VistaSplash extends StackPane {

    public VistaSplash(Runnable siguienteVista) {
        Label titulo = new Label("🐉 MI JUEGO AVENTURA 🏰\nDesarrollado por Juan Eloy Ortiz Lara y Fernando Romero Gil \n(c) Versión 1.0 2025");
        this.getChildren().add(titulo);

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));
        pausa.setOnFinished(e -> siguienteVista.run());
        pausa.play();

        this.setOnKeyPressed(e -> {
            pausa.stop();
            siguienteVista.run();
        });
    }
}
