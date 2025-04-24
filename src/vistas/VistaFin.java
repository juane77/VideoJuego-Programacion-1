package src.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaFin extends VBox {

    public VistaFin(Runnable volverInicio) {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label mensaje = new Label("🎉 ¡Has terminado la partida! 🎉");
        Button botonReiniciar = new Button("Volver al inicio");

        botonReiniciar.setOnAction(e -> volverInicio.run());

        getChildren().addAll(mensaje, botonReiniciar);
    }
}
