package src.vistas;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import java.util.Optional;
import java.util.function.Consumer;
import src.modelos.Jugador;
import src.modelos.Modelo;

public class VistaInicio extends VBox {

    public VistaInicio(Modelo modelo, Consumer<Jugador> iniciarJuego) {
        Label etiqueta = new Label("Introduce tu nombre de jugador:");
        TextField campoNombre = new TextField();
        Button botonEntrar = new Button("Entrar");

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(etiqueta, campoNombre, botonEntrar);

        botonEntrar.setOnAction(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()) {
                try {
                    Jugador jugador;
                    if (modelo.existeJugador(nombre)) {
                        jugador = modelo.cargarJugador(nombre);
                    } else {
                        jugador = pedirDatosJugador(nombre);
                        if (jugador != null) {
                            modelo.guardarJugador(jugador);
                        } else {
                            return;
                        }
                    }
                    iniciarJuego.accept(jugador);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Error al cargar el jugador").showAndWait();
                }
            }
        });
    }

    private Jugador pedirDatosJugador(String nombre) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Nuevo jugador");
        dialogo.setHeaderText("Introduce tu Gmail");
        Optional<String> resultado = dialogo.showAndWait();

        return resultado.filter(gmail -> !gmail.trim().isEmpty())
                        .map(gmail -> new Jugador(nombre, gmail))
                        .orElse(null);
    }
}
